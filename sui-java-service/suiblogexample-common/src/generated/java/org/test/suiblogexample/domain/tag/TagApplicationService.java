// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.domain.tag;

import java.util.Map;
import java.util.List;
import org.dddml.support.criterion.Criterion;
import java.math.BigInteger;
import java.util.Date;
import org.test.suiblogexample.domain.*;
import org.test.suiblogexample.specialization.Event;
import org.test.suiblogexample.domain.Command;

public interface TagApplicationService {
    void when(TagCommands.Create c);

    TagState get(String id);

    Iterable<TagState> getAll(Integer firstResult, Integer maxResults);

    Iterable<TagState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<TagState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<TagState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults);

    long getCount(Iterable<Map.Entry<String, Object>> filter);

    long getCount(Criterion filter);

    TagEvent getEvent(String name, long version);

    TagState getHistoryState(String name, long version);

}

