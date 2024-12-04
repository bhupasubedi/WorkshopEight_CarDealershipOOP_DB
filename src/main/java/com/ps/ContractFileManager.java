package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ContractFileManager {

    public void saveContract(Contract contract){

        try (BufferedWriter  bufferedWriter = new BufferedWriter(new FileWriter("Contracts", true))) {
            if(contract instanceof SalesContract){
                SalesContract sale = (SalesContract) contract;
                String salesContractToAdd = sale.toString();
                bufferedWriter.write(salesContractToAdd+ "\n");

            }else {
                LeaseContract lease = (LeaseContract) contract;
                String leaseContractToAdd = lease.toString();
                bufferedWriter.write(leaseContractToAdd + "\n");
                bufferedWriter.close();


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
