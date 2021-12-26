package com.project.hairsalon.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import com.project.hairsalon.DTO.BillDTO;
import com.project.hairsalon.DTO.BillResponse;
import com.project.hairsalon.DTO.MessageDTO;
import com.project.hairsalon.model.*;
import com.project.hairsalon.service.*;
import com.project.hairsalon.utils.urlUtil;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTML;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BillController {
    @Autowired
    private IBillService billService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IClientService clientService;
    @Autowired

    private IPayementService payementService;
    @Autowired
    private IBillDetailService billDetailService;
    @Autowired
    private IServicesService servicesService;
    @Autowired
    PaypalService paypalService;
    @Autowired
    IShiftService shiftService;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";
    @GetMapping("/bills")
    public List<BillResponse> getAll() {

        List<BillResponse> billResponses = new ArrayList<>();
        List<Bill> bills = billService.findAll();
        List<Services> services;
        for (Bill bill : bills) {
            billResponses.add(billToBillresp(bill));
        }
        return billResponses;
    }

    @GetMapping("/bills/employee")
    public List<BillResponse> findByEmployeePhone(@RequestParam String phone) {
        List<Bill> bills = billService.findBillsByEmployee_Phone(phone);
        List<BillResponse> billResponses = new ArrayList<>();
        for (Bill bill : bills) {
            billResponses.add(billToBillresp(bill));
        }
        return billResponses;
    }
    @GetMapping("/bills/client")
    public List<BillResponse> findByClientPhone(@RequestParam String phone) {
        List<Bill> bills = billService.findBillsByClient_Phone(phone);
        List<BillResponse> billResponses = new ArrayList<>();
        for (Bill bill : bills) {
            billResponses.add(billToBillresp(bill));
        }
        return billResponses;
    }


    @PostMapping("/bills/pay/paypal/")
    public String saveBill(HttpServletRequest request, @RequestBody BillDTO billDTO) {
        deleteBill();
        Double total = billDetailService.totalCount(billDTO.getIdServices());
        try {
            Payment payment = paypalService.createPayment(total, "USD", "paypal",
                    "sale","", urlUtil.getBaseURL(request) +"/api/" + CANCEL_URL,
                    urlUtil.getBaseURL(request) +"/api/"+ SUCCESS_URL);

            String paypalId = payment.getId();
            Long billId = saveBill(billDTO,false,paypalId);

            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";

    }
    @GetMapping(value = SUCCESS_URL)
    public ResponseEntity<Void> successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId
    ,HttpServletRequest request) {

        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            Transaction transaction = (Transaction) payment.getTransactions().toArray()[0];
            String billId = transaction.getDescription();

            if (payment.getState().equals("approved")) {
                Bill bill = billService.findBillsByPaypalId(payment.getId());
                bill.setStatus(true);
                billService.save(bill);
                Employee_Shifts employee_shifts = shiftService.findEmployee_ShiftsByEmpIdAndShiftId(bill.getEmployee().getId(),bill.getShift().getId());
                employee_shifts.setWork(false);
                shiftService.saveEmployee_Shifts(employee_shifts);
                return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://hairsalonp110.herokuapp.com/succ3ss")).build();
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
            deleteBill();
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://hairsalonp110.herokuapp.com/f4il")).build();
        }
        deleteBill();
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://hairsalonp110.herokuapp.com/f4il")).build();
    }
    @GetMapping(value = CANCEL_URL)
    public ResponseEntity<Void> redirect(HttpServletRequest request){
        deleteBill();
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://hairsalonp110.herokuapp.com")).build();
    }
    @DeleteMapping("/bills/{id}")
    public ResponseEntity<?> deleteAgency(@PathVariable Long id){
        billService.deleteById(id);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setError_message("Deleted agency id= "+id);
        return ResponseEntity.status(HttpStatus.OK).body(messageDTO);
    }
    public BillResponse billToBillresp(Bill bill) {
        List<Services> services= new ArrayList<>();
        BillResponse billResponse = new BillResponse();
        billResponse.setId(bill.getId());
        billResponse.setPrice(bill.getPrice());
        billResponse.setClient(bill.getClient());
        billResponse.getClient().setPassword(null);
        billResponse.setEmployee(bill.getEmployee());
        billResponse.setPayment(bill.getPayment());
        billResponse.setShift(bill.getShift());
        billResponse.setStatus(bill.getStatus());
        for (BillDetail billDetail : bill.getBillDetails()) {
            services.add(billDetail.getServices());
        }
        billResponse.setServices(services);
        return billResponse;
    }
    private Long saveBill(BillDTO billDTO,boolean status,String paypalId){
        Bill bill = new Bill();
        bill.setPrice(billDetailService.totalCount(billDTO.getIdServices()));
        bill.setClient(clientService.findClientByPhone(billDTO.getClientPhone()));
        bill.setEmployee(employeeService.findByid(Long.parseLong(billDTO.getEmployeeId())));
        bill.setPayment(payementService.findByid(billDTO.getPaymentId()));
        bill.setShift(shiftService.findByid(billDTO.getShiftId()));
        bill.setStatus(status);
        bill.setPaypalId(paypalId);

        List<BillDetail> billDetails = new ArrayList<>();
        List<Services> servicess = new ArrayList<>();
        for (Long service : billDTO.getIdServices()) {
            Services services = servicesService.findByid(service);
            billDetails.add(new BillDetail(null, bill, services));
            servicess.add(services);
        }
        bill.setBillDetails(billDetails);
        billService.save(bill);
        return bill.getId();
    }
    private void deleteBill(){
        List<Bill> bills = billService.findAll();
        for (Bill bill : bills){
            if (!bill.getStatus()) billService.deleteById(bill.getId());
        }
    }
}
