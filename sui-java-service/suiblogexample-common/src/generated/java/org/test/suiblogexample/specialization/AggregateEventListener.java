package org.test.suiblogexample.specialization;


public interface AggregateEventListener<TA, TS> {

    void eventAppended(AggregateEvent<TA, TS> e);

}
