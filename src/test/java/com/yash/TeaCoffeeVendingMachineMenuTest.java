package com.yash;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.yash.impl.TeaCoffeeVendingMachineMenu;

@RunWith(MockitoJUnitRunner.class) 
public class TeaCoffeeVendingMachineMenuTest {

	@InjectMocks
	private TeaCoffeeVendingMachineMenu teaCoffeeVendingMachineMenu;

	@Mock
	private ScannerTeaCoffeeMachine scannerTeaCoffeeMachine;

	@Mock
	private VendingMachineOperationsInterface vendingMachineOperationsInterface;

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
	public void shouldReturnTrueToMakeCoffeeForVendingMachine(){
		Integer cupCount = 1;
		String drinkType ="Coffee";

		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(1,1);
		when(vendingMachineOperationsInterface.makeDrinkForVendingMachine(drinkType,cupCount)).thenReturn(true);

		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(false);

		verify(mockAppender, times(5)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldReturnFalseToMakeCoffeeForVendingMachine(){
		Integer cupCount = 0;
		String drinkType ="Coffee";
		
		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(1,0);
		when(vendingMachineOperationsInterface.makeDrinkForVendingMachine(drinkType,cupCount)).thenReturn(false);

		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(false);

		verify(vendingMachineOperationsInterface).makeDrinkForVendingMachine(drinkType, cupCount);
		verify(mockAppender, times(4)).doAppend(captorLoggingEvent.capture());
	}
	@Test
	public void shouldReturnTrueToMakeTeaForVendingMachine(){
		Integer cupCount = 1;
		String drinkType ="Tea";

		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(2,1);
		when(vendingMachineOperationsInterface.makeDrinkForVendingMachine(drinkType,cupCount)).thenReturn(true);

		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(false);

		verify(vendingMachineOperationsInterface).makeDrinkForVendingMachine(drinkType, cupCount);
		verify(mockAppender, times(5)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldReturnFalseToMakeTeaForVendingMachine(){
		Integer cupCount = 0;
		String drinkType ="Tea";

		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(2,0);
		when(vendingMachineOperationsInterface.makeDrinkForVendingMachine(drinkType,cupCount)).thenReturn(false);

		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(false);

		verify(vendingMachineOperationsInterface).makeDrinkForVendingMachine(drinkType, cupCount);
		verify(mockAppender, times(4)).doAppend(captorLoggingEvent.capture());
	}
	@Test
	public void shouldReturnTrueToMakeBlackCoffeeForVendingMachine(){
		Integer cupCount = 1;
		String drinkType ="Black Coffee";

		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(3,1);
		when(vendingMachineOperationsInterface.makeDrinkForVendingMachine(drinkType,cupCount)).thenReturn(true);

		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(false);

		verify(vendingMachineOperationsInterface).makeDrinkForVendingMachine(drinkType, cupCount);
		verify(mockAppender, times(5)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldReturnFalseToMakeBlackCoffeeForVendingMachine(){
		Integer cupCount = 0;
		String drinkType ="Black Coffee";

		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(3,0);
		when(vendingMachineOperationsInterface.makeDrinkForVendingMachine(drinkType,cupCount)).thenReturn(false);

		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(false);

		verify(vendingMachineOperationsInterface).makeDrinkForVendingMachine(drinkType, cupCount);
		verify(mockAppender, times(4)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldReturnTrueToMakeBlackTeaForVendingMachine(){
		Integer cupCount = 1;
		String drinkType ="Black Tea";

		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(4,1);
		when(vendingMachineOperationsInterface.makeDrinkForVendingMachine(drinkType,cupCount)).thenReturn(true);

		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(false);

		verify(vendingMachineOperationsInterface).makeDrinkForVendingMachine(drinkType, cupCount);
		verify(mockAppender, times(5)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldReturnFalseToMakeBlackTeaForVendingMachine(){
		Integer cupCount = 0;
		String drinkType ="Black Tea";

		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(4,0);
		when(vendingMachineOperationsInterface.makeDrinkForVendingMachine(drinkType,cupCount)).thenReturn(false);

		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(false);

		verify(vendingMachineOperationsInterface).makeDrinkForVendingMachine(drinkType, cupCount);
		verify(mockAppender, times(4)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldRefillContainerForVendingMachine(){
		Integer refillContainerChoice = 1, amountToFill = 1;

		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(5,1,1,0);
		doNothing().when(vendingMachineOperationsInterface).refillContainerForVendingMachine(refillContainerChoice,amountToFill,true);

		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(false);

		verify(vendingMachineOperationsInterface).refillContainerForVendingMachine(refillContainerChoice,amountToFill,true);
		verify(mockAppender, times(7)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldCheckTotalSaleForVendingMachine(){

		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(6);
		doNothing().when(vendingMachineOperationsInterface).checkTotalSaleForVendingMachine();

		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(false);

		verify(vendingMachineOperationsInterface).checkTotalSaleForVendingMachine();
		verify(mockAppender, times(3)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldCheckContainerStatusForVendingMachine(){

		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(7);
		doNothing().when(vendingMachineOperationsInterface).checkContainerStatusForVendingMachine();

		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(false);

		verify(vendingMachineOperationsInterface).checkContainerStatusForVendingMachine();
		verify(mockAppender, times(3)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldResetContainerForVendingMachine(){

		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(8);
		doNothing().when(vendingMachineOperationsInterface).resetContainerForVendingMachine();

		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(false);

		verify(vendingMachineOperationsInterface).resetContainerForVendingMachine();
		verify(mockAppender, times(3)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldCheckExitForVendingMachine(){

		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(9);

		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(false);
		
		verify(mockAppender, times(3)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldPrintInvalidChoiceForVendingMachine(){

		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(10);

		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(false);

		verify(mockAppender, times(4)).doAppend(captorLoggingEvent.capture());
	}

	@Test
	public void shouldGoToMainMenuFromRefillContainerForVendingMachine(){

		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(5,6,1,0);

		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(false);

		verify(mockAppender, times(5)).doAppend(captorLoggingEvent.capture());

	}
	
	@Test
	public void shouldContinueRefillChoiceForVendingMachine(){
		Integer refillContainerChoice = 1, amountToFill = 1;

		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(5,1,1,1,0);
		doNothing().when(vendingMachineOperationsInterface).refillContainerForVendingMachine(refillContainerChoice,amountToFill,true);

		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(false);

		verify(vendingMachineOperationsInterface).refillContainerForVendingMachine(refillContainerChoice,amountToFill,true);
		verify(mockAppender, times(11)).doAppend(captorLoggingEvent.capture());
	}
	
	@Test
	public void shouldContinueMainMenuForVendingMachine(){

		Integer cupCount = 1;
		String drinkType ="Coffee";

		when(scannerTeaCoffeeMachine.nextInt()).thenReturn(1,1,9);
		when(vendingMachineOperationsInterface.makeDrinkForVendingMachine(drinkType,cupCount)).thenReturn(true);
		
		teaCoffeeVendingMachineMenu.DisplayMenuForTeaCoffeeVendingMachine(true);

		verify(vendingMachineOperationsInterface).makeDrinkForVendingMachine(drinkType,cupCount);
		verify(mockAppender, times(8)).doAppend(captorLoggingEvent.capture());
	}
}
