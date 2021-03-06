package org.jberet.wildfly.cluster.jms._private;

import org.jboss.logging.Messages;
import org.jboss.logging.annotations.Cause;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageBundle;
import org.jboss.logging.annotations.ValidIdRange;

@MessageBundle(projectCode = "JBERET")
@ValidIdRange(min = 75000, max = 75499)
public interface ClusterJmsMessages {
    ClusterJmsMessages MESSAGES = Messages.getBundle(ClusterJmsMessages.class);

    @Message(id = 75000, value = "Failed to lookup %s")
    IllegalStateException failedToLookup(@Cause Throwable throwable, String name);

    @Message(id = 75001, value = "Failed instantiate naming context")
    IllegalStateException failedToNewNamingContext(@Cause Throwable throwable);

    @Message(id = 75002, value = "Failed in JMS operation")
    IllegalStateException failedInJms(@Cause Throwable throwable);

    @Message(id = 75003, value = "Failed to get job operator")
    IllegalStateException failedToGetJobOperator();

}
