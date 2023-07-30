// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.suiblogexample.domain.blog;

import java.util.*;
import java.util.function.Consumer;
import org.dddml.support.criterion.Criterion;
import java.math.BigInteger;
import java.util.Date;
import org.test.suiblogexample.domain.*;
import org.test.suiblogexample.specialization.*;

public abstract class AbstractBlogApplicationService implements BlogApplicationService {

    private EventStore eventStore;

    protected EventStore getEventStore()
    {
        return eventStore;
    }

    private BlogStateRepository stateRepository;

    protected BlogStateRepository getStateRepository() {
        return stateRepository;
    }

    private BlogStateQueryRepository stateQueryRepository;

    protected BlogStateQueryRepository getStateQueryRepository() {
        return stateQueryRepository;
    }

    private AggregateEventListener<BlogAggregate, BlogState> aggregateEventListener;

    public AggregateEventListener<BlogAggregate, BlogState> getAggregateEventListener() {
        return aggregateEventListener;
    }

    public void setAggregateEventListener(AggregateEventListener<BlogAggregate, BlogState> eventListener) {
        this.aggregateEventListener = eventListener;
    }

    public AbstractBlogApplicationService(EventStore eventStore, BlogStateRepository stateRepository, BlogStateQueryRepository stateQueryRepository) {
        this.eventStore = eventStore;
        this.stateRepository = stateRepository;
        this.stateQueryRepository = stateQueryRepository;
    }

    public void when(BlogCommands.AddArticle c) {
        update(c, ar -> ar.addArticle(c.getArticleId(), c.getOffChainVersion(), c.getCommandId(), c.getRequesterId(), c));
    }

    public void when(BlogCommands.RemoveArticle c) {
        update(c, ar -> ar.removeArticle(c.getArticleId(), c.getOffChainVersion(), c.getCommandId(), c.getRequesterId(), c));
    }

    public BlogState get(String id) {
        BlogState state = getStateRepository().get(id, true);
        return state;
    }

    public Iterable<BlogState> getAll(Integer firstResult, Integer maxResults) {
        return getStateQueryRepository().getAll(firstResult, maxResults);
    }

    public Iterable<BlogState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults) {
        return getStateQueryRepository().get(filter, orders, firstResult, maxResults);
    }

    public Iterable<BlogState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults) {
        return getStateQueryRepository().get(filter, orders, firstResult, maxResults);
    }

    public Iterable<BlogState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults) {
        return getStateQueryRepository().getByProperty(propertyName, propertyValue, orders, firstResult, maxResults);
    }

    public long getCount(Iterable<Map.Entry<String, Object>> filter) {
        return getStateQueryRepository().getCount(filter);
    }

    public long getCount(Criterion filter) {
        return getStateQueryRepository().getCount(filter);
    }

    public BlogEvent getEvent(String id, long version) {
        BlogEvent e = (BlogEvent)getEventStore().getEvent(toEventStoreAggregateId(id), version);
        if (e != null) {
            ((BlogEvent.SqlBlogEvent)e).setEventReadOnly(true); 
        } else if (version == -1) {
            return getEvent(id, 0);
        }
        return e;
    }

    public BlogState getHistoryState(String id, long version) {
        EventStream eventStream = getEventStore().loadEventStream(AbstractBlogEvent.class, toEventStoreAggregateId(id), version - 1);
        return new AbstractBlogState.SimpleBlogState(eventStream.getEvents());
    }


    public BlogAggregate getBlogAggregate(BlogState state) {
        return new AbstractBlogAggregate.SimpleBlogAggregate(state);
    }

    public EventStoreAggregateId toEventStoreAggregateId(String aggregateId) {
        return new EventStoreAggregateId.SimpleEventStoreAggregateId(aggregateId);
    }

    protected void update(BlogCommand c, Consumer<BlogAggregate> action) {
        String aggregateId = c.getId();
        EventStoreAggregateId eventStoreAggregateId = toEventStoreAggregateId(aggregateId);
        BlogState state = getStateRepository().get(aggregateId, false);
        boolean duplicate = isDuplicateCommand(c, eventStoreAggregateId, state);
        if (duplicate) { return; }

        BlogAggregate aggregate = getBlogAggregate(state);
        aggregate.throwOnInvalidStateTransition(c);
        action.accept(aggregate);
        persist(eventStoreAggregateId, c.getOffChainVersion() == null ? BlogState.VERSION_NULL : c.getOffChainVersion(), aggregate, state); // State version may be null!

    }

    private void persist(EventStoreAggregateId eventStoreAggregateId, long version, BlogAggregate aggregate, BlogState state) {
        getEventStore().appendEvents(eventStoreAggregateId, version, 
            aggregate.getChanges(), (events) -> { 
                getStateRepository().save(state); 
            });
        if (aggregateEventListener != null) {
            aggregateEventListener.eventAppended(new AggregateEvent<>(aggregate, state, aggregate.getChanges()));
        }
    }

    protected boolean isDuplicateCommand(BlogCommand command, EventStoreAggregateId eventStoreAggregateId, BlogState state) {
        boolean duplicate = false;
        if (command.getOffChainVersion() == null) { command.setOffChainVersion(BlogState.VERSION_NULL); }
        if (state.getOffChainVersion() != null && state.getOffChainVersion() > command.getOffChainVersion()) {
            Event lastEvent = getEventStore().getEvent(AbstractBlogEvent.class, eventStoreAggregateId, command.getOffChainVersion());
            if (lastEvent != null && lastEvent instanceof AbstractEvent
               && command.getCommandId() != null && command.getCommandId().equals(((AbstractEvent) lastEvent).getCommandId())) {
                duplicate = true;
            }
        }
        return duplicate;
    }

    public static class SimpleBlogApplicationService extends AbstractBlogApplicationService {
        public SimpleBlogApplicationService(EventStore eventStore, BlogStateRepository stateRepository, BlogStateQueryRepository stateQueryRepository)
        {
            super(eventStore, stateRepository, stateQueryRepository);
        }
    }

}

