// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.domain.tagv2;

import java.util.*;
import java.math.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.suiblogexample.domain.*;
import org.test.suiblogexample.specialization.Event;

public interface TagV2State extends VersionedSuiMoveObject
{
    Long VERSION_ZERO = 0L;

    Long VERSION_NULL = VERSION_ZERO - 1;

    String getId();

    String getName();

    Long getOffChainVersion();

    String getCreatedBy();

    Date getCreatedAt();

    String getUpdatedBy();

    Date getUpdatedAt();

    Boolean getActive();

    Boolean getDeleted();

    interface MutableTagV2State extends TagV2State, VersionedSuiMoveObject.MutableVersionedSuiMoveObject {
        void setId(String id);

        void setName(String name);

        void setOffChainVersion(Long offChainVersion);

        void setCreatedBy(String createdBy);

        void setCreatedAt(Date createdAt);

        void setUpdatedBy(String updatedBy);

        void setUpdatedAt(Date updatedAt);

        void setActive(Boolean active);

        void setDeleted(Boolean deleted);


        void mutate(Event e);

        //void when(TagV2Event.TagV2StateCreated e);

        //void when(TagV2Event.TagV2StateMergePatched e);

        //void when(TagV2Event.TagV2StateDeleted e);
    }

    interface SqlTagV2State extends MutableTagV2State {

        boolean isStateUnsaved();

        boolean getForReapplying();
    }
}
