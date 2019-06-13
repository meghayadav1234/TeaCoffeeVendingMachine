package com.yash;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.yash.impl.VendingMachineOperations;

@RunWith(MockitoJUnitRunner.class) 
public class VendingMachineOperationsTest {

	@InjectMocks
	private VendingMachineOperations vendingMachineOperations;

	@Mock
	private Appender mockAppender;

	@Captor
	private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

	@Before
	public void setup() {

		Logger root = Logger.getRootLogger();
		root.addAppender(mockAppender);
		root.setLevel(Level.INFO);
	}

	@After
	public void removeAppender() {
		Logger.getRootLogger().removeAppender(mockAppender);
	}

	@Test
	public void shouldReturnTrueToMakeCoffeeForNumberOfCupAsInput(){
		Integer cupCount = 1;
		String drinkType ="Coffee";

		Boolean expectedOutput = vendingMachineOperations.makeDrinkForVendingMachine(drinkType,cupCount);
		assertTrue(expectedOutput);
	}

	@Test
	public void shouldReturnFalseToMakeTeaForNumberOfCupInput(){
		Integer cupCount = 0;
		String drinkType ="Coffee";

		Boolean expectedOutput = vendingMachineOperations.makeDrinkForVendingMachine(drinkType,cupCount);
		assertFalse(expectedOutput);
	}

	@Test
	public void shouldShowMessageAsNoEnoughMaterialForNoOfCupCount(){
		Integer cupCount = 200;
		String drinkType ="Coffee";

		Boolean expectedOutput = vendingMachineOperations.makeDrinkForVendingMachine(drinkType,cupCount);
		assertFalse(expectedOutput);
	}

	@Test
	public void shouldShowMessageWhenAmountToFillIsLessThanZeroToRefillContainer(){

		Integer refillContainerChoice = 1; 
		Integer amountToFill = -1 ;
		vendingMachineOperations.refillContainerForVendingMachine(refillContainerChoice, amountToFill,false);

		verify(mockAppender, times(1)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldRefillCoffeeContainer(){

		Integer refillContainerChoice = 1; 
		Integer amountToFill = 10 ;
		Integer cupCount = 5;
		String drinkType ="Coffee";

		vendingMachineOperations.makeDrinkForVendingMachine(drinkType,cupCount);
		vendingMachineOperations.refillContainerForVendingMachine(refillContainerChoice, amountToFill,false);
	}

	@Test
	public void shouldShowMessageWhenContainerCurrentCapacityIsMaximumAndnotAbleToRefillCoffeeContainer(){

		Integer refillContainerChoice = 1; 
		Integer amountToFill = 10 ;
		vendingMachineOperations.refillContainerForVendingMachine(refillContainerChoice, amountToFill,false);

		verify(mockAppender, times(1)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldRefillTeaContainer(){

		Integer refillContainerChoice = 2; 
		Integer amountToFill = 10 ;
		Integer cupCount = 5;
		String drinkType ="Tea";

		vendingMachineOperations.makeDrinkForVendingMachine(drinkType,cupCount);
		vendingMachineOperations.refillContainerForVendingMachine(refillContainerChoice, amountToFill, false);
	}

	@Test
	public void shouldShowMessageWhenContainerCurrentCapacityIsMaximumAndnotAbleToRefillTeaContainer(){

		Integer refillContainerChoice = 2; 
		Integer amountToFill = 10 ;

		vendingMachineOperations.refillContainerForVendingMachine(refillContainerChoice, amountToFill ,false);

		verify(mockAppender, times(1)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldRefillWaterContainer(){

		Integer refillContainerChoice = 3; 
		Integer amountToFill = 10 ;
		Integer cupCount = 5;
		String drinkType ="Tea";

		vendingMachineOperations.makeDrinkForVendingMachine(drinkType,cupCount);
		vendingMachineOperations.refillContainerForVendingMachine(refillContainerChoice, amountToFill , false);
	}

	@Test
	public void shouldShowMessageWhenContainerCurrentCapacityIsMaximumAndnotAbleToRefillWaterContainer(){

		Integer refillContainerChoice = 3; 
		Integer amountToFill = 10 ;

		vendingMachineOperations.refillContainerForVendingMachine(refillContainerChoice, amountToFill , false);

		verify(mockAppender, times(1)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldRefillMilkContainer(){

		Integer refillContainerChoice = 4; 
		Integer amountToFill = 10 ;
		Integer cupCount = 5;
		String drinkType ="Tea";

		vendingMachineOperations.makeDrinkForVendingMachine(drinkType,cupCount);
		vendingMachineOperations.refillContainerForVendingMachine(refillContainerChoice, amountToFill , false);
	}

	@Test
	public void shouldShowMessageWhenContainerCurrentCapacityIsMaximumAndnotAbleToRefillMilkContainer(){

		Integer refillContainerChoice = 4; 
		Integer amountToFill = 10 ;

		vendingMachineOperations.refillContainerForVendingMachine(refillContainerChoice, amountToFill , false);

		verify(mockAppender, times(1)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldRefillSugarContainer(){

		Integer refillContainerChoice = 5; 
		Integer amountToFill = 10 ;
		Integer cupCount = 5;
		String drinkType ="Tea";

		vendingMachineOperations.makeDrinkForVendingMachine(drinkType,cupCount);
		vendingMachineOperations.refillContainerForVendingMachine(refillContainerChoice, amountToFill,false);
	}

	@Test
	public void shouldShowMessageWhenContainerCurrentCapacityIsMaximumAndnotAbleToRefillSugarContainer(){

		Integer refillContainerChoice = 5; 
		Integer amountToFill = 10 ;

		vendingMachineOperations.refillContainerForVendingMachine(refillContainerChoice, amountToFill,false);

		verify(mockAppender, times(1)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldCheckSelectedChoiceIsInvalidToRefillContainer(){

		Integer refillContainerChoice = 6; 
		Integer amountToFill = 10 ;
		vendingMachineOperations.refillContainerForVendingMachine(refillContainerChoice, amountToFill,false);
	}

	@Test
	public void shouldCheckTotalSaleForVendingMachine(){

		Integer cupCount = 133;
		String drinkType ="Black Coffee";

		vendingMachineOperations.makeDrinkForVendingMachine(drinkType,cupCount);

		vendingMachineOperations.checkTotalSaleForVendingMachine();

		verify(mockAppender, times(31)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldCheckContainerStatusForVendingMachine(){

		Integer cupCount = 133;
		String drinkType ="Black Coffee";

		vendingMachineOperations.makeDrinkForVendingMachine(drinkType,cupCount);

		vendingMachineOperations.checkContainerStatusForVendingMachine();

		verify(mockAppender, times(12)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldResetContainerForVendingMachine(){

		Integer cupCount = 133;
		String drinkType ="Black Coffee";

		vendingMachineOperations.makeDrinkForVendingMachine(drinkType,cupCount);

		vendingMachineOperations.resetContainerForVendingMachine();

		verify(mockAppender, times(6)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldAddRefillCountInJsonObjToRefillContainer(){

		Integer refillContainerChoice = 1; 
		Integer amountToFill = 10 ;
		Integer cupCount = 50;
		String drinkType ="Black Coffee";

		vendingMachineOperations.makeDrinkForVendingMachine(drinkType,cupCount);
		vendingMachineOperations.refillContainerForVendingMachine(refillContainerChoice, amountToFill,true);
	}

	@Test
	public void shouldDisplayRefillCountFromJsonObjInTotalSaleReport(){

		Integer refillContainerChoice = 1; 
		Integer amountToFill = 10 ;
		Integer cupCount = 50;
		String drinkType ="Black Coffee";

		vendingMachineOperations.makeDrinkForVendingMachine(drinkType,cupCount);
		vendingMachineOperations.refillContainerForVendingMachine(refillContainerChoice, amountToFill,true);

		vendingMachineOperations.checkTotalSaleForVendingMachine();

		verify(mockAppender, times(33)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldCheckNullForRefillCountFromJsonObjInTotalSaleReport(){

		Integer refillContainerChoice = 1; 
		Integer amountToFill = 10 ;
		Integer cupCount = 50;
		String drinkType ="Black Coffee";

		vendingMachineOperations.makeDrinkForVendingMachine(drinkType,cupCount);
		vendingMachineOperations.refillContainerForVendingMachine(refillContainerChoice, amountToFill,true);
		vendingMachineOperations.refillContainerForVendingMachine(refillContainerChoice, amountToFill,true);
		vendingMachineOperations.checkTotalSaleForVendingMachine();
	}

	@Test
	public void shouldAddTotalCupCountInJsonObjInMakeDrink(){

		Integer cupCountOne = 50;
		String drinkTypeOne ="Coffee";

		Integer cupCountTwo = 1;
		String drinkTypeTwo ="Coffee";

		vendingMachineOperations.makeDrinkForVendingMachine(drinkTypeOne,cupCountOne);
		vendingMachineOperations.makeDrinkForVendingMachine(drinkTypeTwo,cupCountTwo);
	}
}
