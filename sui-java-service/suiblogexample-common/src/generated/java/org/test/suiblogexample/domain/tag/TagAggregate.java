// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.domain.tag;

import java.util.List;
import java.math.BigInteger;
import java.util.Date;
import org.test.suiblogexample.domain.*;
import org.test.suiblogexample.specialization.Event;
import org.test.suiblogexample.domain.Command;

public interface TagAggregate {
    TagState getState();

    List<Event> getChanges();

    void create(Long offChainVersion, String commandId, String requesterId, TagCommands.Create c);

    void throwOnInvalidStateTransition(Command c);
}
