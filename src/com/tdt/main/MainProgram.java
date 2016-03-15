package com.tdt.main;

import com.tdt.rmi_action_impl.CounterImpl;
import com.tdt.rmi_actions.Counter;

import java.math.BigInteger;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MainProgram {

    public static void main(String[] args) {
        //TODO start our server here

        try {

            String codeBasePath = MainProgram.class
                    .getProtectionDomain().getCodeSource().getLocation().toString();
            System.setProperty("java.rmi.server.codebase", codeBasePath);
            System.setProperty("java.security.policy",
                    PolicyFileLoader.getLocationOfPolicyFile());
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            CounterImpl counter = new CounterImpl();
            counter.setN(BigInteger.valueOf(1028171));
            Counter stub = (Counter) UnicastRemoteObject.exportObject(counter, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("counter", stub);
            System.out.println("Server started");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
