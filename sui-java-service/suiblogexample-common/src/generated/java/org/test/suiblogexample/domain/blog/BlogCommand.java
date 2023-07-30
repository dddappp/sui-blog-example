// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.domain.blog;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.suiblogexample.domain.*;
import org.test.suiblogexample.domain.Command;
import org.test.suiblogexample.specialization.DomainError;

public interface BlogCommand extends Command {

    String getId();

    void setId(String id);

    Long getOffChainVersion();

    void setOffChainVersion(Long offChainVersion);

    static void throwOnInvalidStateTransition(BlogState state, Command c) {
        if (state.getOffChainVersion() == null) {
            if (isCommandCreate((BlogCommand)c)) {
                return;
            }
            throw DomainError.named("premature", "Can't do anything to unexistent aggregate");
        }
        if (state.getDeleted() != null && state.getDeleted()) {
            throw DomainError.named("zombie", "Can't do anything to deleted aggregate.");
        }
        if (isCommandCreate((BlogCommand)c))
            throw DomainError.named("rebirth", "Can't create aggregate that already exists");
    }

    static boolean isCommandCreate(BlogCommand c) {
        if (c.getOffChainVersion().equals(BlogState.VERSION_NULL))
            return true;
        return false;
    }

}
