// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.domain.article;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.suiblogexample.domain.*;
import org.test.suiblogexample.domain.Command;
import org.test.suiblogexample.specialization.DomainError;

public interface CommentCommand extends Command {

    BigInteger getCommentSeqId();

    void setCommentSeqId(BigInteger commentSeqId);

}

