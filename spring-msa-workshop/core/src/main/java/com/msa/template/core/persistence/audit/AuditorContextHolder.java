package com.msa.template.core.persistence.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorContextHolder implements AuditorAware<Long> {

	@Override
	public Optional<Long> getCurrentAuditor() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication == null || !authentication.isAuthenticated()) {
//			return Optional.of(0L);
//		}
//		if (authentication.getPrincipal().equals("anonymousUser")) {
//			return Optional.of(0L);
//		}
//
//		return Optional.of(((UserAccount) authentication.getPrincipal()).getId());
		return Optional.of(0L);
	}
}
