package com.freshsip.billservice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillingService {

    @Autowired
    private BillingRepo billingRepository;

    @Autowired
    private RestTemplate restTemplate;

    public OrderDTO getOrderById(Long cartId) {
        return restTemplate.getForObject("http://order-service:8082/FreshSip/Order/adminUser/CartById/" + cartId, OrderDTO.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    private BillingDTO convertToDTO(Billing billing) {
        return modelMapper.map(billing, BillingDTO.class);
    }

    private Billing convertToEntity(BillingDTO billingDTO) {
        return modelMapper.map(billingDTO, Billing.class);
    }

    public List<BillingDTO> getAllBills() {
        return billingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<BillingDTO> getBillById(int id) {
        return billingRepository.findById(id).map(this::convertToDTO);
    }

    public BillingDTO createBill(BillingDTO billingDTO) {
        Billing billing = new Billing();

        billing.setDate(LocalDate.parse(billingDTO.getDate()));
        billing.setTime(LocalTime.parse(billingDTO.getTime()));
        billing.setCartId(billingDTO.getCartId());
        billing.setCash(billingDTO.getCash());
        billing.setBalance(billingDTO.getBalance());

        // Fetch total
        OrderDTO order = getOrderById(billingDTO.getCartId());
        billing.setFullTotal(order != null ? order.getFull_total() : 0.0);

        return convertToDTO(billingRepository.save(billing));
    }



    public BillingDTO updateBill(int id, BillingDTO updatedBillDTO) {
        return billingRepository.findById(id).map(existingBill -> {
            // Update the cash and balance
            existingBill.setCash(updatedBillDTO.getCash());
            existingBill.setBalance(updatedBillDTO.getBalance());

            // Save the updated bill
            Billing updatedBill = billingRepository.save(existingBill);

            return convertToDTO(updatedBill);
        }).orElseThrow(() -> new RuntimeException("Bill not found with id " + id));
    }


    public void deleteBill(int id) {
        billingRepository.deleteById(id);
    }
}
