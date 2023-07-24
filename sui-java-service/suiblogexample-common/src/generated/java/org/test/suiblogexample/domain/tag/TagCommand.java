// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.domain.tag;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.suiblogexample.domain.*;
import org.test.suiblogexample.domain.Command;
import org.test.suiblogexample.specialization.DomainError;

public interface TagCommand extends Command {

    String getName();

    void setName(String name);

    String getId_();

    void setId_(String id);

    Long getOffChainVersion();

    void setOffChainVersion(Long offChainVersion);

    static void throwOnInvalidStateTransition(TagState state, Command c) {
        if (state.getOffChainVersion() == null) {
            if (isCommandCreate((TagCommand)c)) {
                return;
            }
            throw DomainError.named("premature", "Can't do anything to unexistent aggregate");
        }
        if (state.getDeleted() != null && state.getDeleted()) {
            throw DomainError.named("zombie", "Can't do anything to deleted aggregate.");
        }
        if (isCommandCreate((TagCommand)c))
            throw DomainError.named("rebirth", "Can't create aggregate that already exists");
    }

    static boolean isCommandCreate(TagCommand c) {
        if (c.getOffChainVersion().equals(TagState.VERSION_NULL))
            return true;
        return false;
    }

}

