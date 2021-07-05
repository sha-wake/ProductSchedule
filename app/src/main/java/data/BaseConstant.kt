package data

object BaseConstant {
    const val FSTID  = "fstId"
    const val FSTID_DEFAULT_VALUE  = ""
    const val FUNCID = "funcId"
    const val FUNCID_DEFAULT_VALUE  = ""
    const val NAME = "name"
    const val PWD = "pwd"
    const val USER = "user"
    const val USER_DEFAULT_VALUR = ""
    const val SAVE = "save"
    const val LANG = "lang"
    const val LANG_DEFAULT_VALUE = "0"
    const val PDACODE = "deviceId"
    const val PDACODE_DEFAULT_VALUE = ""
    const val CUR_IP = "localIp"
    const val IP_DATAS = "ipDatas"
    const val FIRST_GET_IP_DATAS = "firstGetIpDatas"
    const val MAX_BARCODES = 150
    const val VERSION = "version"
    const val MENU_JSON = "[\n" +
            "    {\n" +
            "        \"menuId\":\"100\",\n" +
            "        \"menuName\":\"mnuInStock\",\n" +
            "        \"menuTitle\":\"入库\",\n" +
            "        \"parId\":\"0\",\n" +
            "        \"sonsum\":\"1\",\n" +
            "        \"priv\":\"1\",\n" +
            "        \"child\":[\n" +
            "            {\n" +
            "                \"menuId\":\"110\",\n" +
            "                \"menuName\":\"mnuToStock\",\n" +
            "                \"menuTitle\":\"成品待入库\",\n" +
            "                \"parId\":\"100\",\n" +
            "                \"sonsum\":\"0\",\n" +
            "                \"priv\":\"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"menuId\":\"120\",\n" +
            "                \"menuName\":\"mnuBuyingIn\",\n" +
            "                \"menuTitle\":\"外购入库\",\n" +
            "                \"parId\":\"100\",\n" +
            "                \"sonsum\":\"0\",\n" +
            "                \"priv\":\"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"menuId\":\"130\",\n" +
            "                \"menuName\":\"mnuOtherIn\",\n" +
            "                \"menuTitle\":\"其他入库\",\n" +
            "                \"parId\":\"100\",\n" +
            "                \"sonsum\":\"0\",\n" +
            "                \"priv\":\"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"menuId\":\"150\",\n" +
            "                \"menuName\":\"mnuRetPro\",\n" +
            "                \"menuTitle\":\"销售退货\",\n" +
            "                \"parId\":\"100\",\n" +
            "                \"sonsum\":\"0\",\n" +
            "                \"priv\":\"0\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"menuId\":\"200\",\n" +
            "        \"menuName\":\"mnuOutStock\",\n" +
            "        \"menuTitle\":\"出库\",\n" +
            "        \"parId\":\"0\",\n" +
            "        \"sonsum\":\"1\",\n" +
            "        \"priv\":\"1\",\n" +
            "        \"child\":[\n" +
            "            {\n" +
            "                \"menuId\":\"412\",\n" +
            "                \"menuName\":\"mnuPickingOut\",\n" +
            "                \"menuTitle\":\"拣货出库\",\n" +
            "                \"parId\":\"200\",\n" +
            "                \"sonsum\":\"0\",\n" +
            "                \"priv\":\"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"menuId\":\"220\",\n" +
            "                \"menuName\":\"mnuArrangingOut\",\n" +
            "                \"menuTitle\":\"领用出库\",\n" +
            "                \"parId\":\"200\",\n" +
            "                \"sonsum\":\"0\",\n" +
            "                \"priv\":\"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"menuId\":\"222\",\n" +
            "                \"menuName\":\"mnuUpPackOut\",\n" +
            "                \"menuTitle\":\"拆托出库\",\n" +
            "                \"parId\":\"200\",\n" +
            "                \"sonsum\":\"0\",\n" +
            "                \"priv\":\"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"menuId\":\"230\",\n" +
            "                \"menuName\":\"mnuOtherOut\",\n" +
            "                \"menuTitle\":\"其他出库\",\n" +
            "                \"parId\":\"200\",\n" +
            "                \"sonsum\":\"0\",\n" +
            "                \"priv\":\"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"menuId\":\"240\",\n" +
            "                \"menuName\":\"mnuReworkOut\",\n" +
            "                \"menuTitle\":\"返工出库\",\n" +
            "                \"parId\":\"200\",\n" +
            "                \"sonsum\":\"0\",\n" +
            "                \"priv\":\"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"menuId\":\"226\",\n" +
            "                \"menuName\":\"mnuReworkOut\",\n" +
            "                \"menuTitle\":\"报废出库\",\n" +
            "                \"parId\":\"200\",\n" +
            "                \"sonsum\":\"0\",\n" +
            "                \"priv\":\"0\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"menuId\":\"300\",\n" +
            "        \"menuName\":\"mnuCurStock\",\n" +
            "        \"menuTitle\":\"库存\",\n" +
            "        \"parId\":\"0\",\n" +
            "        \"sonsum\":\"1\",\n" +
            "        \"priv\":\"1\",\n" +
            "        \"child\":[\n" +
            "            {\n" +
            "                \"menuId\":\"310\",\n" +
            "                \"menuName\":\"mnuStockCheck\",\n" +
            "                \"menuTitle\":\"库存盘点\",\n" +
            "                \"parId\":\"300\",\n" +
            "                \"sonsum\":\"0\",\n" +
            "                \"priv\":\"0\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"menuId\":\"500\",\n" +
            "        \"menuName\":\"mnuProduction\",\n" +
            "        \"menuTitle\":\"生产\",\n" +
            "        \"parId\":\"0\",\n" +
            "        \"sonsum\":\"1\",\n" +
            "        \"priv\":\"1\",\n" +
            "        \"child\":[\n" +
            "            {\n" +
            "                \"menuId\":\"510\",\n" +
            "                \"menuName\":\"mnuGrouping\",\n" +
            "                \"menuTitle\":\"组托单\",\n" +
            "                \"parId\":\"500\",\n" +
            "                \"sonsum\":\"0\",\n" +
            "                \"priv\":\"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"menuId\":\"520\",\n" +
            "                \"menuName\":\"mnuBoxing\",\n" +
            "                \"menuTitle\":\"装箱单\",\n" +
            "                \"parId\":\"500\",\n" +
            "                \"sonsum\":\"0\",\n" +
            "                \"priv\":\"0\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "]"
//    val NAV_ANIM = navOptions {
//        anim {
//            enter = R.anim.common_slide_in_right
//            exit = R.anim.common_slide_out_left
//            popEnter = R.anim.common_slide_in_left
//            popExit = R.anim.common_slide_out_right
//        }
//    }
}