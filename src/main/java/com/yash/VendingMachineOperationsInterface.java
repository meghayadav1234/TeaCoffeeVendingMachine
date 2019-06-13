package com.yash;

import java.util.HashMap;
import java.util.Map;

import com.yash.dto.ContainerMaterialDTO;

public interface VendingMachineOperationsInterface {

	public Boolean makeDrinkForVendingMachine(String drinkType, Integer cupCount);

	public void refillContainerForVendingMachine(Integer refillContainerChoice, Integer amountToFill,Boolean isCalledFromMainMenu);

	public void checkTotalSaleForVendingMachine();

	public void checkContainerStatusForVendingMachine();

	public void resetContainerForVendingMachine();

	public default Map<String,ContainerMaterialDTO> getMaterialRequirementIncludingWaste(){
		Map<String,ContainerMaterialDTO> materialRequirementMap = new HashMap<>();

		ContainerMaterialDTO materialRequirementCoffee =new ContainerMaterialDTO(0, 5, 17, 23, 88);
		materialRequirementMap.put("Coffee", materialRequirementCoffee);

		ContainerMaterialDTO materialRequirementTea =new ContainerMaterialDTO(6, 0, 17, 65, 44);
		materialRequirementMap.put("Tea", materialRequirementTea);

		ContainerMaterialDTO materialRequirementBlackCoffee =new ContainerMaterialDTO(0, 3, 17, 112, 0);
		materialRequirementMap.put("Black Coffee", materialRequirementBlackCoffee);

		ContainerMaterialDTO materialRequirementBlackTea =new ContainerMaterialDTO(3, 0, 17, 112, 0);
		materialRequirementMap.put("Black Tea", materialRequirementBlackTea);

		return materialRequirementMap;
	}

	public default Map<String,Integer> getCostOfDrinkPerEachCupForVendingMachine(){

		Map<String,Integer> drinkCostPerCup= new HashMap<>();
		drinkCostPerCup.put("Tea", 10);
		drinkCostPerCup.put("Black Tea", 5);
		drinkCostPerCup.put("Coffee", 15);
		drinkCostPerCup.put("Black Coffee", 10);

		return drinkCostPerCup;
	}

	public default Map<String,ContainerMaterialDTO> getWasteOfMaterial(){
		Map<String,ContainerMaterialDTO> materialRequirementMap = new HashMap<>();

		ContainerMaterialDTO materialRequirementCoffee =new ContainerMaterialDTO(0, 1, 2, 3, 8);
		materialRequirementMap.put("Coffee", materialRequirementCoffee);

		ContainerMaterialDTO materialRequirementTea =new ContainerMaterialDTO(1, 0, 2, 5, 4);
		materialRequirementMap.put("Tea", materialRequirementTea);

		ContainerMaterialDTO materialRequirementBlackCoffee =new ContainerMaterialDTO(0, 0, 2, 12, 0);
		materialRequirementMap.put("Black Coffee", materialRequirementBlackCoffee);

		ContainerMaterialDTO materialRequirementBlackTea =new ContainerMaterialDTO(0, 0, 2, 12, 0);
		materialRequirementMap.put("Black Tea", materialRequirementBlackTea);

		return materialRequirementMap;
	}

}
