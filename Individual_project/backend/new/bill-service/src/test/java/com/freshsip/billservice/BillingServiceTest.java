//package com.freshsip.billservice;
//
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//
//class BillingServiceTest {
//
//    @Mock
//    private BillingRepo billingRepo;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private BillingService billingService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetAllBills() {
//        // Mock data
//        Billing billing1 = new Billing();
//        billing1.setBillId(1L);
//
//        Billing billing2 = new Billing();
//        billing2.setBillId(2L);
//
//
//        BillingDTO billingDTO1 = new BillingDTO();
//        billingDTO1.setBillId(1L);
//        BillingDTO billingDTO2 = new BillingDTO();
//        billingDTO2.setBillId(2L);
//
//        when(billingRepo.findAll()).thenReturn(Arrays.asList(billing1, billing2));
//        when(modelMapper.map(billing1, BillingDTO.class)).thenReturn(billingDTO1);
//        when(modelMapper.map(billing2, BillingDTO.class)).thenReturn(billingDTO2);
//
//        // Call service
//        List<BillingDTO> result = billingService.getAllBills();
//
//        // Verify
//        assertEquals(1, result.get(0).getBillId());
//        assertEquals(2, result.get(1).getBillId());
//
//
//        verify(billingRepo, times(1)).findAll();
//    }
//
//    @Test
//    void testGetBillById_BillNotFound() {
//        // Mock behavior for non-existing ID
//        when(billingRepo.findById(99)).thenReturn(Optional.empty());
//
//        // Call service
//        Optional<BillingDTO> result = billingService.getBillById(99);
//
//        // Verify
//        assertFalse(result.isPresent(), "Expected no bill for the given ID");
//        verify(billingRepo, times(1)).findById(99);
//    }
//
//    @Test
//    void testCreateBill_ValidInput() {
//        BillingDTO billingDTO = new BillingDTO();
//        billingDTO.setCartId(1L);
//
//        Billing billing = new Billing();
//        billing.setCartId(1L);
//
//        Billing savedBilling = new Billing();
//        savedBilling.setBillId(1L);
//        savedBilling.setCartId(1L);
//        savedBilling.setFullTotal(500.0);
//
//        BillingDTO savedBillingDTO = new BillingDTO();
//        savedBillingDTO.setBillId(1L);
//        savedBillingDTO.setCartId(1L);
//        savedBillingDTO.setFullTotal(500.0);
//
//        OrderDTO mockOrder = new OrderDTO();
//        mockOrder.setCart_id(1L);
//        mockOrder.setFull_total(500.0);
//
//        when(modelMapper.map(billingDTO, Billing.class)).thenReturn(billing);
//        when(restTemplate.getForObject("http://localhost:8082/orders/1", OrderDTO.class)).thenReturn(mockOrder);
//        when(billingRepo.save(billing)).thenReturn(savedBilling);
//        when(modelMapper.map(savedBilling, BillingDTO.class)).thenReturn(savedBillingDTO);
//
//        BillingDTO result = billingService.createBill(billingDTO);
//
//        assertNotNull(result);
//        assertEquals(1L, result.getBillId());
//        assertEquals(500.0, result.getFullTotal());
//
//        verify(restTemplate, times(1)).getForObject("http://localhost:8082/orders/1", OrderDTO.class);
//        verify(billingRepo, times(1)).save(billing);
//    }
//
//
//
//}
