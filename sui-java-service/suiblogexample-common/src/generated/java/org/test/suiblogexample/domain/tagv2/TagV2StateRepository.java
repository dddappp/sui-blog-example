// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.domain.tagv2;

import java.util.*;
import org.dddml.support.criterion.Criterion;
import java.math.BigInteger;
import java.util.Date;
import org.test.suiblogexample.domain.*;

public interface TagV2StateRepository {
    TagV2State get(String id, boolean nullAllowed);

    void save(TagV2State state);

    void merge(TagV2State detached);
}

