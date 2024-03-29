// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.domain.tagv2.hibernate;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.suiblogexample.domain.*;
import org.hibernate.Session;
import org.hibernate.Criteria;
//import org.hibernate.criterion.Order;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.SessionFactory;
import org.test.suiblogexample.domain.tagv2.*;
import org.test.suiblogexample.specialization.*;
import org.test.suiblogexample.specialization.hibernate.*;
import org.springframework.transaction.annotation.Transactional;

public class HibernateTagV2StateRepository implements TagV2StateRepository {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() { return this.sessionFactory; }

    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
    
    private static final Set<String> readOnlyPropertyPascalCaseNames = new HashSet<String>(Arrays.asList("Id", "Name", "Version", "OffChainVersion", "CreatedBy", "CreatedAt", "UpdatedBy", "UpdatedAt", "Active", "Deleted"));
    
    private ReadOnlyProxyGenerator readOnlyProxyGenerator;
    
    public ReadOnlyProxyGenerator getReadOnlyProxyGenerator() {
        return readOnlyProxyGenerator;
    }

    public void setReadOnlyProxyGenerator(ReadOnlyProxyGenerator readOnlyProxyGenerator) {
        this.readOnlyProxyGenerator = readOnlyProxyGenerator;
    }

    @Transactional(readOnly = true)
    public TagV2State get(String id, boolean nullAllowed) {
        TagV2State.SqlTagV2State state = (TagV2State.SqlTagV2State)getCurrentSession().get(AbstractTagV2State.SimpleTagV2State.class, id);
        if (!nullAllowed && state == null) {
            state = new AbstractTagV2State.SimpleTagV2State();
            state.setId(id);
        }
        if (getReadOnlyProxyGenerator() != null && state != null) {
            return (TagV2State) getReadOnlyProxyGenerator().createProxy(state, new Class[]{TagV2State.SqlTagV2State.class}, "getStateReadOnly", readOnlyPropertyPascalCaseNames);
        }
        return state;
    }

    public void save(TagV2State state) {
        TagV2State s = state;
        if (getReadOnlyProxyGenerator() != null) {
            s = (TagV2State) getReadOnlyProxyGenerator().getTarget(state);
        }
        if(s.getOffChainVersion() == null) {
            getCurrentSession().save(s);
        } else {
            getCurrentSession().update(s);
        }

        if (s instanceof Saveable)
        {
            Saveable saveable = (Saveable) s;
            saveable.save();
        }
        getCurrentSession().flush();
    }

    public void merge(TagV2State detached) {
        TagV2State persistent = getCurrentSession().get(AbstractTagV2State.SimpleTagV2State.class, detached.getId());
        if (persistent != null) {
            merge(persistent, detached);
            getCurrentSession().save(persistent);
        } else {
            getCurrentSession().save(detached);
        }
        getCurrentSession().flush();
    }

    private void merge(TagV2State persistent, TagV2State detached) {
        ((AbstractTagV2State) persistent).merge(detached);
    }

}

