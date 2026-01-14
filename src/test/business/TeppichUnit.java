package test.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.experimental.theories.internal.BooleanSupplier;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import business.*;
import business.teppich.Teppich;

class TeppichUnit 
{

	Teppich teppich;
	@BeforeEach
	void setUp()
	{
		String[] colors = {"rot", "orang"};
		this.teppich = new Teppich("123", "L�ufer", 150, 400, colors);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		teppich = null;
	}
	
	
	@Test
	void test() 
	{
		assertTrue(()->teppich.getKategorie().equals("L�ufer"));
		Throwable exc = assertThrows(IllegalArgumentException.class,() -> new Teppich("123","L�ufer",150,400, null));
		//exc.printStackTrace();

	}

}
