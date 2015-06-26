package org.gpms.web.bondedItemIssue;

import org.springframework.stereotype.Component;

@Component
public class BondedItemExclusiveGateway {

	Boolean bondedItemApproved;

	/**
	 * @return the bondedItemApproved
	 */
	public Boolean getBondedItemApproved() {
		return bondedItemApproved;
	}

	/**
	 * @param bondedItemApproved
	 *            the bondedItemApproved to set
	 */
	public void setBondedItemApproved(Boolean bondedItemApproved) {
		this.bondedItemApproved = bondedItemApproved;
	}

}
