/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.gpms.web.gpmsBusinessSrv.assets.AssetMgmtBusinessSrv;
import org.gpms.web.gpmsBusinessSrv.assets.AssetModel;
import org.gpms.web.gpmsBusinessSrv.assets.AssetTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author narenda.kumar
 * 
 */
@Controller
public class AssetController {

	@Autowired
	AssetMgmtBusinessSrv assetMgmtBusinessSrv;

	@RequestMapping(value = "/newAsset", method = RequestMethod.GET)
	public ModelAndView newAsset(@ModelAttribute AssetBean assetBean,
			Model model) {

		List<AssetTypeModel> assetTypeModelLst = assetMgmtBusinessSrv
				.getAllAssetType();

		System.out.println("assetTypeModelLst  : " + assetTypeModelLst.size());

		model.addAttribute("assetTypeModelLst", assetTypeModelLst);
		model.addAttribute("assetBean", assetBean);

		return new ModelAndView("assets/newAsset");
	}

	@RequestMapping(value = "/newAsset", method = RequestMethod.POST, params = "createAsset")
	public String createAsset(@ModelAttribute @Valid AssetBean assetBean,
			BindingResult result, Model model) throws IOException {

		List<AssetTypeModel> assetTypeModelLst = assetMgmtBusinessSrv
				.getAllAssetType();

		AssetModel assetModel = new AssetModel();
		assetModel.setAssetBarCode(assetBean.getAssetBarCode());
		assetModel.setAssetTypeId(assetBean.getAssetTypeId());
		assetModel.setAssetPurchaseDate(assetBean.getAssetPurchaseDate());
		assetModel.setAssetRemovalDate(assetBean.getAssetRemovalDate());
		AssetModel returnModel = assetMgmtBusinessSrv.createAsset(assetModel);
		assetBean.setAssetId(returnModel.getAssetId());

		if (returnModel.getAssetId() != null) {
			model.addAttribute("isDisabled", "true");
		}

		model.addAttribute("assetTypeModelLst", assetTypeModelLst);
		model.addAttribute("assetBean", assetBean);

		return "assets/newAsset";
	}

	@RequestMapping(value = "/newAssetType", method = RequestMethod.GET)
	public ModelAndView newAssetType(
			@ModelAttribute AssetTypeBean assetTypeBean, BindingResult result,
			Model model) {

		model.addAttribute("assetTypeBean", assetTypeBean);

		return new ModelAndView("assets/newAssetType");
	}

	@RequestMapping(value = "/newAssetType", method = RequestMethod.POST, params = "createAssetType")
	public String createAssetType(
			@ModelAttribute @Valid AssetTypeBean assetTypeBean,
			BindingResult result, Model model) throws IOException {

		AssetTypeModel assetTypeModel = new AssetTypeModel();
		assetTypeModel.setAssetTypeName(assetTypeBean.getAssetTypeName());
		assetTypeModel.setAssetTypeDesc(assetTypeBean.getAssetTypeDesc());
		AssetTypeModel returnModel = assetMgmtBusinessSrv
				.createAssetType(assetTypeModel);
		assetTypeBean.setAssetTypeId(returnModel.getAssetTypeId());

		if (returnModel.getAssetTypeId() != null) {
			model.addAttribute("isDisabled", "true");
		}

		model.addAttribute("assetTypeBean", assetTypeBean);

		return "assets/newAssetType";
	}

}
