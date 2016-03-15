package com.tdt.rmi_action_impl;


import com.tdt.rmi_actions.Counter;

import java.math.BigInteger;
import java.rmi.RemoteException;

public class CounterImpl implements Counter {

    protected volatile BigInteger currentPosition = BigInteger.valueOf(2);
    protected volatile BigInteger p = null;
    protected volatile BigInteger q = null;
    /**
     * very large complex integer N that we want to analyze
     */
    protected volatile BigInteger n = null;

    @Override
    public synchronized void update(BigInteger RSAKey, BigInteger position, BigInteger p, boolean success) throws RemoteException {

        if (!success) {
            currentPosition = position.compareTo(currentPosition) > 0 ? position : currentPosition;
        } else {
            this.p = p;
            q = RSAKey.divide(p);
        }
    }

    @Override
    public BigInteger getCurrentPosition() throws RemoteException {
        return currentPosition.equals(n) ? null : currentPosition;
    }

    @Override
    public BigInteger getResult() throws RemoteException {
        return p;
    }

    @Override
    public BigInteger getGivenN() throws RemoteException {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }
}
