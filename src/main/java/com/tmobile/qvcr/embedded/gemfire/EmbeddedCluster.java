package com.tmobile.qvcr.embedded.gemfire;

import java.io.File;
import java.util.Scanner;

import org.apache.geode.distributed.ServerLauncher;

public class EmbeddedCluster {

	public static void main(String[] args) throws RuntimeException {

		// String[] fileNames = new String[] { "server1" };
		// for (String file : fileNames) {
		// File theDir = new File(file);
		// if (!theDir.exists()) {
		// try {
		// theDir.mkdir();
		// } catch (Exception ex) {
		// throw new RuntimeException("Unable to create directory " + theDir.getName(),
		// ex);
		// }
		// }
		// }

		ServerLauncher serverLauncher = new ServerLauncher.Builder().setMemberName("server1").setServerPort(40404)
				.set("mcast-port", "0").set("log-level", "info").set("use-cluster-configuration", "false")
				.set("conserve-sockets", "false").set("statistic-archive-file", "server1.gfs")
				.set("log-file", "server1.log").set("cache-xml-file", "server-cache.xml")
				.set("locators", "localhost[10334]").set("start-locator", "localhost[10334]").set("jmx-manager", "true")
				.set("jmx-manager-start", "true").build();
		serverLauncher.start();
		System.out.println("Cache server successfully started");

		Scanner sc = new Scanner(System.in);
		boolean complete = false;
		System.out.println("\nTo stop embedded GemFire cluster enter shutdown\n");
		do {
			if (sc.hasNextLine()) {
				String input = sc.nextLine();
				if (input != null) {
					if ("shutdown".equals(input.toLowerCase())) {
						complete = true;
					} else {
						System.out.println("Invalid request - to shutdown embedded GemFire Cluster type shutdown\n");
					}
				}
			}
		} while (!complete);
		sc.close();
		serverLauncher.stop();
		System.exit(0);
	}
}