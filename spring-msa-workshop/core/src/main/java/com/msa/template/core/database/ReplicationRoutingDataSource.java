package com.msa.template.core.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		DatabaseType currentDataSourceType = DatabaseType.WRITE;

		boolean transactionActive = TransactionSynchronizationManager.isActualTransactionActive();

		if (transactionActive) {
			boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
			if (readOnly) {
				currentDataSourceType = DatabaseType.READ;
			}
		}
		log.trace("### current dataSourceType : {}", currentDataSourceType);
		return currentDataSourceType;
	}
}
