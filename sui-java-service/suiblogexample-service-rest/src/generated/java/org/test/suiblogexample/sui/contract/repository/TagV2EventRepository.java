// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.sui.contract.repository;

import org.test.suiblogexample.domain.tagv2.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface TagV2EventRepository extends JpaRepository<AbstractTagV2Event, TagV2EventId> {

    List<AbstractTagV2Event> findByStatusIsNull();

    AbstractTagV2Event.TagV2Created findFirstTagV2CreatedByOrderBySuiTimestampDesc();

}
