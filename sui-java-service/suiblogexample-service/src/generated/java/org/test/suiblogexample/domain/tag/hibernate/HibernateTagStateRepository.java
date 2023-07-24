// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.domain.tag.hibernate;

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
import org.test.suiblogexample.domain.tag.*;
import org.test.suiblogexample.specialization.*;
import org.test.suiblogexample.specialization.hibernate.*;
import org.springframework.transaction.annotation.Transactional;

public class HibernateTagStateRepository implements TagStateRepository {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() { return this.sessionFactory; }

    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
    
    private static final Set<String> readOnlyPropertyPascalCaseNames = new HashSet<String>(Arrays.asList("Name", "Version", "OffChainVersion", "CreatedBy", "CreatedAt", "UpdatedBy", "UpdatedAt", "Active", "Deleted"));
    
    private ReadOnlyProxyGenerator readOnlyProxyGenerator;
    
    public ReadOnlyProxyGenerator getReadOnlyProxyGenerator() {
        return readOnlyProxyGenerator;
    }

    public void setReadOnlyProxyGenerator(ReadOnlyProxyGenerator readOnlyProxyGenerator) {
        this.readOnlyProxyGenerator = readOnlyProxyGenerator;
    }

    @Transactional(readOnly = true)
    public TagState get(String id, boolean nullAllowed) {
        TagState.SqlTagState state = (TagState.SqlTagState)getCurrentSession().get(AbstractTagState.SimpleTagState.class, id);
        if (!nullAllowed && state == null) {
            state = new AbstractTagState.SimpleTagState();
            state.setName(id);
        }
        if (getReadOnlyProxyGenerator() != null && state != null) {
            return (TagState) getReadOnlyProxyGenerator().createProxy(state, new Class[]{TagState.SqlTagState.class}, "getStateReadOnly", readOnlyPropertyPascalCaseNames);
        }
        return state;
    }

    public void save(TagState state) {
        TagState s = state;
        if (getReadOnlyProxyGenerator() != null) {
            s = (TagState) getReadOnlyProxyGenerator().getTarget(state);
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

    public void merge(TagState detached) {
        TagState persistent = getCurrentSession().get(AbstractTagState.SimpleTagState.class, detached.getName());
        if (persistent != null) {
            merge(persistent, detached);
            getCurrentSession().merge(detached);
        } else {
            getCurrentSession().save(detached);
        }
        getCurrentSession().flush();
    }

    private void merge(TagState persistent, TagState detached) {
        ((TagState.MutableTagState) detached).setOffChainVersion(persistent.getOffChainVersion());
    }

}

