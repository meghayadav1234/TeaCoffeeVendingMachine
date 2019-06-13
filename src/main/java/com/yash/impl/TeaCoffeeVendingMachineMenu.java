package com.yash.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.yash.ScannerTeaCoffeeMachine;
import com.yash.VendingMachineOperationsInterface;

public class TeaCoffeeVendingMachineMenu {

	ScannerTeaCoffeeMachine scanner = new ScannerTeaCoffeeMachine();

	VendingMachineOperationsInterface VendingMachineOperationsInterface = new VendingMachineOperations();

	private final static Logger logger = LogManager.getLogger(TeaCoffeeVendingMachineMenu.class);

	public void DisplayMenuForTeaCoffeeVendingMachine(Boolean isContinueFalg) {
		do{
			logger.info("\nMenu : ");
			logger.info("1. Coffee"	+ "\n2. Tea" + "\n3. Black Coffee" + "\n4. Black Tea"+ "\n5. Refill Container"
					+ "\n6. Check Total Sale" + "\n7. Container Status" + "\n8. Reset Container" + "\n9. Exit");
			logger.info("\nSelect Menu :");
			Integer menuChoice = scanner.nextInt();
			switch(menuChoice){
			case 1 : 
				prepareDrinkForVendingMachine("Coffee");
				break;
			case 2 : 
				prepareDrinkForVendingMachine("Tea");
				break;
			case 3 : 
				prepareDrinkForVendingMachine("Black Coffee");
				break;
			case 4 : 
				prepareDrinkForVendingMachine("Black Tea");
				break;
			case 5 : 
				menuListForRefillContainer();
				break;
			case 6 : 
				VendingMachineOperationsInterface.checkTotalSaleForVendingMachine();
				break;
			case 7 : 
				VendingMachineOperationsInterface.checkContainerStatusForVendingMachine();
				break;
			case 8 : 
				VendingMachineOperationsInterface.resetContainerForVendingMachine();
				break;
			case 9 : 
				isContinueFalg = false;
				break;
			default:
				logger.warn("Invalid Choice");
			}
		}while(isContinueFalg);
	}

	private void prepareDrinkForVendingMachine(String drinkType) {
		Boolean isSucess;
		Integer cupCount;
		logger.info("\nEnter Cup Count :");
		cupCount = scanner.nextInt();
		isSucess = VendingMachineOperationsInterface.makeDrinkForVendingMachine(drinkType,cupCount);
		if(isSucess){
			logger.info( drinkType + "is Ready");
		}
	}

	private void menuListForRefillContainer() {
		Boolean isCalledFromMainMenu = true;
		Boolean isContinueToRefillContainer = false;
		do{
			logger.info("1. Coffee Container" + "\n2. Tea Container" + "\n3. Water Container" + "\n4. Milk Container"
					+ "\n5. Sugar Container" + "\n6. Back to Menu");
			logger.info("Select Option to refill container : ");
			Integer refillContainerChoice = scanner.nextInt();
			if(refillContainerChoice == 6){
				isContinueToRefillContainer = false;
			}else{
				logger.info(" Enter quantity to fill container(in ml or gm) :");
				Integer amountToFill = scanner.nextInt();
				VendingMachineOperationsInterface.refillContainerForVendingMachine(refillContainerChoice,amountToFill,isCalledFromMainMenu);
				logger.info(" Do you want to continue refill container press 1 otherwise 0.");
				isContinueToRefillContainer = (scanner.nextInt() == 1) ? true : false;
				isCalledFromMainMenu = false;
			}
		}while(isContinueToRefillContainer);
	}
}
