package com.unzer.shop_slice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.unzer.payment.Unzer;

@SpringBootTest
class ShopSliceApplicationTests {

	@MockitoBean
	private Unzer unzer;
	@Test
	void contextLoads() {
	}

}
