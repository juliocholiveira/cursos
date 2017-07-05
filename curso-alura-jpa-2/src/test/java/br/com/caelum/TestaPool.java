package br.com.caelum;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestaPool {

	public static void main(String[] args) throws PropertyVetoException,
			SQLException, InterruptedException {

		ComboPooledDataSource dataSource = (ComboPooledDataSource) new JpaConfigurator()
				.getDataSource();

		for (int i = 0; i < 25; i++) {
			Connection connection = dataSource.getConnection();

			// Exibe o nr de conexões ocupadas
			System.out.println(dataSource.getNumBusyConnections());
			// Exibe o nr de conexões ociosas
			System.out.println(dataSource.getNumIdleConnections());
			System.out.println("");
			
			Thread.sleep(5000);

		}

	}

}
