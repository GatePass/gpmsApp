/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.assets;

import org.gpms.web.bondedItemIssue.BondedItemIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author narenda.kumar
 * 
 */
@Service
public class IssueAssetsBusinessSrv {

	@Autowired
	BondedItemIssue bondedItemIssue;

	public void IssueBondedItems() {

		bondedItemIssue.startBondItemIssueProcess();

	}

}
