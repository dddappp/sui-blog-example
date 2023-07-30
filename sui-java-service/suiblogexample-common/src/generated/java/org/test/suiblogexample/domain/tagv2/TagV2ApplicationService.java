// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.domain.tagv2;

import java.util.Map;
import java.util.List;
import org.dddml.support.criterion.Criterion;
import java.math.BigInteger;
import java.util.Date;
import org.test.suiblogexample.domain.*;
import org.test.suiblogexample.specialization.Event;
import org.test.suiblogexample.domain.Command;

public interface TagV2ApplicationService {
    void when(TagV2Commands.Create c);

    TagV2State get(String id);

    Iterable<TagV2State> getAll(Integer firstResult, Integer maxResults);

    Iterable<TagV2State> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<TagV2State> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<TagV2State> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults);

    long getCount(Iterable<Map.Entry<String, Object>> filter);

    long getCount(Criterion filter);

    TagV2Event getEvent(String id, long version);

    TagV2State getHistoryState(String id, long version);

}
