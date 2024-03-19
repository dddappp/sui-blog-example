// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.domain.tag;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.suiblogexample.domain.*;
import org.test.suiblogexample.specialization.Event;

public interface TagEvent extends Event, SuiEventEnvelope, SuiMoveEvent, HasStatus {

    interface SqlTagEvent extends TagEvent {
        TagEventId getTagEventId();

        boolean getEventReadOnly();

        void setEventReadOnly(boolean readOnly);
    }

    interface TagCreated extends TagEvent {
    }

    String getName();

    //void setName(String name);

    BigInteger getVersion();
    
    //void setVersion(BigInteger version);

    String getId_();
    
    //void setId_(String id);

    String getCreatedBy();

    void setCreatedBy(String createdBy);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    String getCommandId();

    void setCommandId(String commandId);


}

