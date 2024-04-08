package com.msa.template.inventory;

import com.msa.template.inventory.config.InventoryConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import java.util.TimeZone;

@Slf4j
@EnableAspectJAutoProxy
@SpringBootApplication
@Import({InventoryConfig.class})
public class InventoryApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		log.info("Set default time zone = " + TimeZone.getDefault());
		new SpringApplicationBuilder()
			.sources(InventoryApplication.class)
			.web(WebApplicationType.SERVLET)
			.bannerMode(Banner.Mode.OFF)
			.build()
			.run(args);
	}
}
