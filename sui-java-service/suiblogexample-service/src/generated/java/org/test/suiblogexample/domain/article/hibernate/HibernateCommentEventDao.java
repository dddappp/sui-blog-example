// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.domain.article.hibernate;

import java.math.BigInteger;
import java.util.Date;
import org.test.suiblogexample.domain.*;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.test.suiblogexample.domain.article.*;
import org.test.suiblogexample.specialization.*;
import org.springframework.transaction.annotation.Transactional;

public class HibernateCommentEventDao implements CommentEventDao {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() { return this.sessionFactory; }

    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void save(CommentEvent e)
    {
        getCurrentSession().save(e);
        if (e instanceof Saveable)
        {
            Saveable saveable = (Saveable) e;
            saveable.save();
        }
    }


    @Transactional(readOnly = true)
    @Override
    public Iterable<CommentEvent> findByArticleEventId(ArticleEventId articleEventId)
    {
        Criteria criteria = getCurrentSession().createCriteria(AbstractCommentEvent.class);
        Junction partIdCondition = Restrictions.conjunction()
            .add(Restrictions.eq("commentEventId.articleId", articleEventId.getId()))
            .add(Restrictions.eq("commentEventId.version", articleEventId.getVersion()))
            ;
        return criteria.add(partIdCondition).list();
    }

}

