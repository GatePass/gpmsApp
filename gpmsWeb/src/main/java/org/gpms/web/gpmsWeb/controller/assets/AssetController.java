/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.gpms.web.gpmsBusinessSrv.assets.AssetMgmtBusinessSrv;
import org.gpms.web.gpmsBusinessSrv.assets.AssetModel;
import org.gpms.web.gpmsBusinessSrv.assets.AssetTypeModel;
import org.gpms.web.gpmsBusinessSrv.util.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author narenda.kumar
 * 
 */
@Controller
public class AssetController {

	private static final Logger logger = Logger
			.getLogger(AssetController.class);

	@Autowired
	AssetMgmtBusinessSrv assetMgmtBusinessSrv;

	@RequestMapping(value = "/newAsset", method = RequestMethod.GET)
	public ModelAndView newAsset(@ModelAttribute AssetBean assetBean,
			Model model) {

		List<AssetTypeModel> assetTypeModelLst = assetMgmtBusinessSrv
				.getAllAssetType();

		model.addAttribute("assetTypeModelLst", assetTypeModelLst);
		model.addAttribute("assetBean", assetBean);

		return new ModelAndView("assets/newAsset");
	}

	@RequestMapping(value = "/newAsset", method = RequestMethod.POST, params = "createAsset")
	public String createAsset(@ModelAttribute @Valid AssetBean assetBean,
			BindingResult result, Model model) throws IOException {

		logger.debug("createAsset");

		List<AssetTypeModel> assetTypeModelLst = assetMgmtBusinessSrv
				.getAllAssetType();

		AssetModel returnModel = null;

		if (assetBean != null) {
			AssetModel assetModel = copyBeanToModel(assetBean);

			if (assetModel != null) {
				assetModel
						.setAssetStatus(ApplicationConstants.ASSET_AVAILABLE_STATUS);
				returnModel = assetMgmtBusinessSrv.createAsset(assetModel);
			}
			assetBean.setAssetId(returnModel.getAssetId());
		}

		if (returnModel.getAssetId() != null) {
			model.addAttribute("isDisabled", "true");
		}

		model.addAttribute("assetTypeModelLst", assetTypeModelLst);
		model.addAttribute("assetBean", assetBean);

		return "assets/newAsset";
	}

	@RequestMapping(value = "/modifyDeleteAsset", method = RequestMethod.GET)
	public ModelAndView modifyDeleteAsset(@ModelAttribute AssetBean assetBean,
			Model model) {
		String flowType = (String) model.asMap().get("flowType");

		assetBean.setFlowType(flowType);
		model.addAttribute("flowType", flowType);
		model.addAttribute("isDisabled", "true");

		return new ModelAndView("assets/modifyDeleteAsset");
	}

	@RequestMapping(value = "/modifyDeleteAsset", method = RequestMethod.POST, params = "getAssetData")
	public ModelAndView getAssetData(@ModelAttribute AssetBean assetBean,
			Model model) {

		String flowType = assetBean.getFlowType();

		if (assetBean != null && assetBean.getAssetId() != null) {
			AssetModel assetModel = assetMgmtBusinessSrv.getAssetById(assetBean
					.getAssetId());
			if (assetModel != null) {
				assetBean = copyModelToBean(assetModel);
			}
		}

		model.addAttribute("assetBean", assetBean);
		model.addAttribute("flowType", flowType);
		model.addAttribute("isDisabled", "false");

		return new ModelAndView("assets/modifyDeleteAsset");
	}

	@RequestMapping(value = "/modifyDeleteAsset", method = RequestMethod.POST, params = "modifyAsset")
	public String modifyAsset(@ModelAttribute @Valid AssetBean assetBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs)
			throws IOException {

		List<AssetTypeModel> assetTypeModelLst = assetMgmtBusinessSrv
				.getAllAssetType();

		AssetModel returnModel = null;

		if (assetBean != null) {
			AssetModel assetModel = copyBeanToModel(assetBean);
			if (assetModel != null) {
				returnModel = assetMgmtBusinessSrv.modifyAsset(assetModel);
			}
			assetBean = copyModelToBean(returnModel);
		}

		if (returnModel != null && returnModel.getAssetId() != null) {
			model.addAttribute("isDisabled", "true");
		}

		model.addAttribute("assetTypeModelLst", assetTypeModelLst);
		model.addAttribute("assetBean", assetBean);

		model.addAttribute("isDisabled", "false");
		model.addAttribute("flowType", "modifyAsset");
		redirectAttrs.addFlashAttribute("flowType", "modifyAsset");
		return "redirect:modifyDeleteAsset";
	}

	@RequestMapping(value = "/modifyDeleteAsset", method = RequestMethod.POST, params = "deleteAsset")
	public String deleteAsset(@ModelAttribute @Valid AssetBean assetBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs)
			throws IOException {

		if (assetBean != null && assetBean.getAssetId() != null) {
			String assetId = assetBean.getAssetId();
			assetMgmtBusinessSrv.deleteAssetById(assetId);
		}

		model.addAttribute("flowType", "deleteAsset");
		model.addAttribute("isDisabled", "false");
		redirectAttrs.addFlashAttribute("flowType", "deleteAsset");
		return "redirect:modifyDeleteAsset";
	}

	private AssetModel copyBeanToModel(AssetBean assetBean) {

		AssetModel assetModel = new AssetModel();
		assetModel.setAssetId(assetBean.getAssetId());
		assetModel.setAssetBarCode(assetBean.getAssetBarCode());
		assetModel.setAssetTypeId(assetBean.getAssetTypeId());
		assetModel.setAssetPurchaseDate(assetBean.getAssetPurchaseDate());
		assetModel.setAssetRemovalDate(assetBean.getAssetRemovalDate());
		assetModel.setAssetStatus(assetBean.getAssetStatus());
		return assetModel;
	}

	private AssetBean copyModelToBean(AssetModel assetModel) {

		AssetBean assetBean = new AssetBean();
		assetBean.setAssetId(assetModel.getAssetId());
		assetBean.setAssetBarCode(assetModel.getAssetBarCode());
		assetBean.setAssetTypeId(assetModel.getAssetTypeId());
		assetBean.setAssetPurchaseDate(assetModel.getAssetPurchaseDate());
		assetBean.setAssetRemovalDate(assetModel.getAssetRemovalDate());
		assetBean.setAssetStatus(assetModel.getAssetStatus());
		return assetBean;
	}

	// ********************************AssetType*************************************************
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
