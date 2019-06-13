package com.yash.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.yash.VendingMachineOperationsInterface;
import com.yash.dto.ContainerMaterialDTO;

public class VendingMachineOperations implements VendingMachineOperationsInterface {

	private final static Logger logger = LogManager.getLogger(VendingMachineOperations.class);

	private Map<String,Integer> containerRemainingCapacityMap = maximumContainerCapacityMap();

	private JsonObject jsonObject = new JsonObject();

	@Override
	public Boolean makeDrinkForVendingMachine(String drinkType, Integer cupCount) {

		Boolean isSucess = false;
		if(cupCount > 0 ){
			Map<String,ContainerMaterialDTO> materialRequirementMap = getMaterialRequirementIncludingWaste();
			Integer totalCupCount = cupCount;
			JsonArray  jsonArray = new JsonArray();
			Map<String,Integer> containerUsedTempMap = new HashMap<>();

			containerUsedTempMap.put("Tea", materialRequirementMap.get(drinkType).getTea() * cupCount);
			containerUsedTempMap.put("Coffee", materialRequirementMap.get(drinkType).getCoffee() * cupCount);
			containerUsedTempMap.put("Sugar", materialRequirementMap.get(drinkType).getSugar() *cupCount);
			containerUsedTempMap.put("Milk", materialRequirementMap.get(drinkType).getMilk() * cupCount);
			containerUsedTempMap.put("Water", materialRequirementMap.get(drinkType).getWater() * cupCount);

			if(updateUsedAndRemainingContainerCapacityInMap(containerUsedTempMap, cupCount)){
				Optional<JsonElement> optional = Optional.ofNullable(jsonObject.get(drinkType));
				if(optional.isPresent()){
					totalCupCount +=jsonObject.get(drinkType).getAsInt();
				}
				jsonArray.add(totalCupCount);
				jsonObject.add(drinkType,  jsonArray);
				isSucess = true;
			}
		}
		return isSucess;
	}

	@Override
	public void refillContainerForVendingMachine(Integer refillContainerChoice, Integer amountToFill, Boolean isCalledFromMainMenu) {

		Integer refillContainerCount = 0;
		Optional<JsonElement> optional = Optional.ofNullable(jsonObject.get("Refill Count"));
		if(optional.isPresent()){
			refillContainerCount = jsonObject.get("Refill Count").getAsInt();
		}
		Map<String , Integer> maximumContainerCapacityMap = maximumContainerCapacityMap();
		String key = null;
		if(refillContainerChoice == 1 ){
			key = "Coffee" ;
		}else if(refillContainerChoice == 2){
			key = "Tea" ;
		}else if(refillContainerChoice == 3){
			key = "Water" ;
		}else if(refillContainerChoice == 4){
			key = "Milk" ;
		}else if(refillContainerChoice == 5){
			key = "Sugar" ;
		}
		Optional<String> optionalKey = Optional.ofNullable(key);
		if(optionalKey.isPresent()){
			Integer containerEmptyAmout = maximumContainerCapacityMap.get(key) - containerRemainingCapacityMap.get(key);
			if(amountToFill < 0){
				logger.error("Amount should be greater than zero.");
			}else if(amountToFill > containerEmptyAmout){
				logger.error("Container Maximum capacity is "+ maximumContainerCapacityMap.get(key) +" And you can fill only " + containerEmptyAmout + " amount.");
			}else{
				Integer refillAmount = containerRemainingCapacityMap.get(key) + amountToFill;
				containerRemainingCapacityMap.put(key , refillAmount);
				logger.info(amountToFill  + " quantity added in "+ key + " Container.");
				if(isCalledFromMainMenu){
					refillContainerCount++;
					jsonObject.addProperty("Refill Count",refillContainerCount);
				}
			}
		}else{
			logger.warn("Invalid Choice");
		}
	}

	@Override
	public void checkTotalSaleForVendingMachine() {
		
		Map<String , Integer> maximumContainerCapacityMap = maximumContainerCapacityMap();
		ContainerMaterialDTO containerMaterialDTO = getTotalSaleReportDrinkwise();
		
		logger.info("\n----------Total Tea-Coffee Sale Report----------------");
		logger.info("Total Cup : "+ containerMaterialDTO.getCupCount());
		logger.info("Total Price : "+ containerMaterialDTO.getTotalPrice());
		logger.info("Container Maximum Capacity  : ");
		maximumContainerCapacityMap.entrySet().forEach(x -> logger.info("\t" + x.getKey() + "\t"+x.getValue()));
		logger.info("Total Current Container Capacity  : ");
		containerRemainingCapacityMap.entrySet().forEach(x -> logger.info("\t" + x.getKey() + "\t"+x.getValue()));
		logger.info("\nTotal Container Waste : ");
		logger.info("\tMilk Container -: "+ containerMaterialDTO.getMilk());
		logger.info("\tWater Container -: "+ containerMaterialDTO.getWater());
		logger.info("\tSugar Container -: "+ containerMaterialDTO.getSugar());
		logger.info("\tTea Container -: "+ containerMaterialDTO.getTea());
		logger.info("\tCoffee Container -: "+ containerMaterialDTO.getCoffee() + "\n");

		Optional<JsonElement> optional = Optional.ofNullable(jsonObject.get("Refill Count"));
		if(optional.isPresent()){
			logger.info("\nRefill Count  :"+ jsonObject.get("Refill Count").getAsInt() + "\n");
		}
	}

	@Override
	public void checkContainerStatusForVendingMachine() {

		Map<String , Integer> maximumContainerCapacityMap = maximumContainerCapacityMap();
		logger.info("Container Maximum Capacity  : ");
		maximumContainerCapacityMap.entrySet().forEach(x -> logger.info("\t" + x.getKey() + "\t"+x.getValue()));
		logger.info("Current Container Capacity  : ");
		containerRemainingCapacityMap.entrySet().forEach(x -> logger.info("\t" + x.getKey() + "\t"+x.getValue()));
	}

	@Override
	public void resetContainerForVendingMachine() {

		containerRemainingCapacityMap = maximumContainerCapacityMap();
		logger.info("Current Container Capacity : ");
		containerRemainingCapacityMap.entrySet().forEach(x -> logger.info("\t"+ x.getKey() +" : "+ x.getValue()));
	}

	private Map<String,Integer> maximumContainerCapacityMap(){

		Map<String,Integer> containerMaxCapacityMap = new HashMap<>();
		containerMaxCapacityMap.put("Tea", 2000);
		containerMaxCapacityMap.put("Coffee", 2000);
		containerMaxCapacityMap.put("Sugar", 8000);
		containerMaxCapacityMap.put("Milk", 10000);
		containerMaxCapacityMap.put("Water", 15000);
		return containerMaxCapacityMap;
	}

	private Boolean updateUsedAndRemainingContainerCapacityInMap(Map<String,Integer> containerUsedTempMap ,Integer cupCount) {

		Integer isToUpdateInContainerCount = 0;
		Boolean isSucess = false;
		Map<String , Integer> maximumContainerCapacityMap = maximumContainerCapacityMap();

		for(String key : maximumContainerCapacityMap.keySet()){
			if(containerUsedTempMap.get(key) > containerRemainingCapacityMap.get(key)){
				isToUpdateInContainerCount++;
				logger.info("No enough quantity in "+ key +" Container");
			}
		}
		if(isToUpdateInContainerCount == 0){
			maximumContainerCapacityMap.keySet().forEach(key -> {
				containerRemainingCapacityMap.put(key, containerRemainingCapacityMap.get(key) - containerUsedTempMap.get(key));
			});
			isSucess = true;
		}
		return isSucess;
	}
	
	private ContainerMaterialDTO getTotalSaleReportDrinkwise(){

		Map<String,Integer> drinkCostPerCup = getCostOfDrinkPerEachCupForVendingMachine();
		Map<String,ContainerMaterialDTO> materialWaste = getWasteOfMaterial();

		Integer totalCup = 0, totalPrice = 0;
		Integer totalSugarWaste = 0, totalMilkWaste = 0, totalTeaWaste = 0 , totalCoffeeWaste = 0 ,totalWaterWaste = 0;

		logger.info("---------------Total Tea-Coffee Sale Report Drink Wise-------------------");
		for(Entry<String, Integer> drinkType : drinkCostPerCup.entrySet()){
			Optional<JsonElement> optional = Optional.ofNullable(jsonObject.get(drinkType.getKey()));
			if(optional.isPresent()){
				ContainerMaterialDTO  containerMaterialDTOWaste =  materialWaste.get(drinkType.getKey());

				logger.info("\nDrink Type : "+ drinkType.getKey()); 
				Integer cupCount = jsonObject.get(drinkType.getKey()).getAsInt();
				totalCup += cupCount;
				totalPrice += drinkType.getValue() * jsonObject.get(drinkType.getKey()).getAsInt();

				totalMilkWaste += cupCount * containerMaterialDTOWaste.getMilk();
				totalWaterWaste += cupCount * containerMaterialDTOWaste.getWater();
				totalSugarWaste += cupCount * containerMaterialDTOWaste.getSugar();
				totalTeaWaste += cupCount * containerMaterialDTOWaste.getTea(); 
				totalCoffeeWaste += cupCount * containerMaterialDTOWaste.getCoffee();

				logger.info("\tCup Count : "+ cupCount);
				logger.info("\tPrice : "+ drinkType.getValue() * jsonObject.get(drinkType.getKey()).getAsInt());

				logger.info("\nTotal Drinkwise Waste : ");
				logger.info("\tMilk Container -: "+ cupCount * containerMaterialDTOWaste.getMilk());
				logger.info("\tWater Container -: "+ cupCount * containerMaterialDTOWaste.getWater());
				logger.info("\tSugar Container -: "+ cupCount * containerMaterialDTOWaste.getSugar());
				logger.info("\tTea Container -: "+ cupCount * containerMaterialDTOWaste.getTea());
				logger.info("\tCoffee Container -: "+ cupCount* containerMaterialDTOWaste.getCoffee());
			}
		}
		ContainerMaterialDTO  containerMaterialDTO = new ContainerMaterialDTO( totalTeaWaste, totalCoffeeWaste, totalSugarWaste, totalWaterWaste,totalMilkWaste);
		containerMaterialDTO.setCupCount(totalCup);
		containerMaterialDTO.setTotalPrice(totalPrice);
		return containerMaterialDTO;
	}
}

