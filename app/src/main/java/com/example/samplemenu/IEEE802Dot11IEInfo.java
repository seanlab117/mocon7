package com.example.samplemenu;

import java.nio.ByteBuffer;
import java.util.Hashtable;
import java.util.Map;

public class IEEE802Dot11IEInfo {
    // assigned Element ID numbers
    public static class ID {
        public static final int SSID = 0;
        public static final int SUPPORTED_RATES = 1;
        // 2: reserved
        public static final int DSSS_PARAMETER_SET = 3;
        // 4: reserved
        public static final int TIM = 5;
        public static final int IBSS_PARAMETER_SET = 6;
        public static final int COUNTRY = 7;
        // 8-9: reserved
        public static final int REQUEST = 10;
        public static final int BSS_LOAD = 11;
        public static final int EDCA_PARAMETER_SET = 12;
        public static final int TSPEC = 13;
        public static final int TCLAS = 14;
        public static final int SCHEDULE = 15;
        public static final int CHALLENGE_TEXT = 16;
        // 17-31: reserved
        public static final int POWER_CONSTRAINT = 32;
        public static final int POWER_CAPABILITY = 33;
        public static final int TPC_REQUEST = 34;
        public static final int TPC_REPORT = 35;
        public static final int SUPPORTED_CHANNELS = 36;
        public static final int CHANNEL_SWITCH_ANNOUNCEMENT = 37;
        public static final int MEASUREMENT_REQUEST = 38;
        public static final int MEASUREMENT_REPORT = 39;
        public static final int QUIET = 40;
        public static final int IBSS_DFS = 41;
        public static final int ERP = 42;
        public static final int TS_DELAY = 43;
        public static final int TCLAS_PROCESSING = 44;
        public static final int HT_CAPABILITIES = 45;
        public static final int QOS_CAPABILITY = 46;
        public static final int ERP_INFORMATION_OLD = 47; // obsolete
        public static final int RSN = 48;
        // 49: reserved
        public static final int EXTENDED_SUPPORT_RATES = 50;
        public static final int AP_CHANNEL_REPORT = 51;
        public static final int NEIGHBOR_REPORT = 52;
        public static final int RCPI = 53;
        public static final int MDE = 54;
        public static final int FTE = 55;
        public static final int TIE = 56;
        public static final int RDE = 57;
        public static final int DSE_REGISTERED_LOCATION = 58;
        public static final int SUPPORTED_OPERATING_CLASSES = 59;
        public static final int EXTENDED_CHANNEL_SWITCH_ANNOUNCEMENT = 60;
        public static final int HT_OPERATION = 61;
        public static final int SECONDARY_CHANNEL_OFFSET = 62;
        public static final int BSS_AVERAGE_ACCESS_DELAY = 63;
        public static final int ANTENNA = 64;
        public static final int RSNI = 65;
        public static final int MEASUREMENT_PILOT_TRANSMISSION = 66;
        public static final int BSS_AVAILABLE_ADMISSION_CAPACITY = 67;
        public static final int BSS_AC_ACCESS_DELAY = 68;
        public static final int TIME_ADVERTISEMENT = 69;
        public static final int RM_ENABLED_CAPABILITIES = 70;
        public static final int MULTIPLE_BSSID = 71;
        public static final int _20_40_BSS_COEXISTENCE = 72;
        public static final int _20_40_BSS_INTOLERANT_CHANNEL_REPORT = 73;
        public static final int OVERLAPPING_BSS_SCAN_PARAMETERS = 74;
        public static final int RIC_DESCRIPTOR = 75;
        public static final int MME = 76;  // Management MIC Element (11w)
        // 77: reserved
        public static final int EVENT_REQUEST = 78;
        public static final int EVENT_REPORT = 79;
        public static final int DIAGNOSTIC_REQUEST = 80;
        public static final int DIAGNOSTIC_REPORT = 81;
        public static final int LOCATION_PARAMETERS = 82;
        public static final int NONTRANSMITTED_BSSID_CAPABILITY = 83;
        public static final int SSID_LIST = 84;
        public static final int MULTIPLE_BSSID_INDEX = 85;
        public static final int FMS_DESCRIPTOR = 86;
        public static final int FMS_REQUEST = 87;
        public static final int FMS_RESPONSE = 88;
        public static final int QOS_TRAFFIC_CAPABILITY = 89;
        public static final int BSS_MAX_IDLE_PERIOD = 90;
        public static final int TFS_REQUEST = 91;
        public static final int TFS_RESPONSE = 92;
        public static final int WNM_SLEEP_MODE = 93;
        public static final int TIM_BROADCAST_REQUEST = 94;
        public static final int TIM_BROADCAST_RESPONSE = 95;
        public static final int COLOCATED_INTERFERENCE_REPORT = 96;
        public static final int CHANNEL_USAGE = 97;
        public static final int TIME_ZONE = 98;
        public static final int DMS_REQUEST = 99;
        public static final int DMS_RESPONSE = 100;
        public static final int LINK_IDENTIFIER = 101;
        public static final int WAKEUP_SCHEDULE = 102;
        // 103: reserved
        public static final int CHANNEL_SWITCH_TIMING = 104;
        public static final int PTI_CONTROL = 105;
        public static final int TPU_BUFFER_STATUS = 106;
        public static final int INTERWORKING = 107;
        public static final int ADVERTISEMENT_PROTOCOL = 108;
        public static final int EXPEDITED_BANDWIDTH_REQUEST = 109;
        public static final int QOS_MAP = 110;
        public static final int ROAMING_CONSORTIUM = 111;
        public static final int EMERGENCY_ALERT_IDENTIFIER = 112;
        public static final int MESH_CONFIGURATION = 113;
        public static final int MESH_ID = 114;
        public static final int MESH_LINK_METRIC_REPORT = 115;
        public static final int CONGESTION_NOTIFICATION = 116;
        public static final int MESH_PEERING_MANAGEMENT = 117;
        public static final int MESH_CHANNEL_SWITCH_PARAMETERS = 118;
        public static final int MESH_AWAKE_WINDOW = 119;
        public static final int BEACON_TIMING = 120;
        public static final int MCCAOP_SETUP_REQUEST = 121;
        public static final int MCCAOP_SETUP_REPLY = 122;
        public static final int MCCAOP_ADVERTISEMENT = 123;
        public static final int MCCAOP_TEARDOWN = 124;
        public static final int GANN = 125;
        public static final int RANN = 126;
        public static final int EXTENDED_CAPABILITIES = 127;
        // 128-129: reserved
        public static final int PREQ = 130;
        public static final int PREP = 131;
        public static final int PERR = 132;
        // 133-136: reserved
        public static final int PXU = 137;
        public static final int PXUC = 138;
        public static final int AUTHENTICATED_MESH_PEERING_EXCHANGE = 139;
        public static final int MIC = 140;
        public static final int DESTINATION_URI = 141;
        public static final int U_APSD_COEXISTENCE = 142;
        public static final int DMG_WAKEUP_SCHEDULE = 143;
        public static final int EXTENDED_SCHEDULE = 144;
        public static final int STA_AVAILABILITY = 145;
        public static final int DMG_TSPEC = 146;
        public static final int NEXT_DMG_ATI = 147;
        public static final int DMG_CAPABILITIES = 148;
        // 149-150: reserved
        public static final int DMG_OPERATION = 151;
        public static final int DMG_BSS_PARAMETER_CHANGE = 152;
        public static final int DMG_BEAM_REFINEMENT = 153;
        public static final int CHANNEL_MEASUREMENT_FEEDBACK = 154;
        // 155-156: reserved
        public static final int AWAKE_WINDOW = 157;
        public static final int MULTI_BAND = 158;
        public static final int ADDBA_EXTENSION = 159;
        public static final int NEXT_PCP_LIST = 160;
        public static final int PCP_HANDOVER = 161;
        public static final int DMG_LINK_MARGIN = 162;
        public static final int SWITCHING_STREAM = 163;
        public static final int SESSION_TRANSITION = 164;
        // 165: reserved
        public static final int CLUSTER_REPORT = 166;
        public static final int RELAY_CAPABILITIES = 167;
        public static final int RELAY_TRANSFER_PARAMETER_SET = 168;
        public static final int BEAMLINK_MAINTENANCE = 169;
        public static final int MMS = 170;
        public static final int U_PID = 171;
        public static final int DMG_LINK_ADAPTION_ACKNOWLEDGMENT = 172;
        // 173: reserved
        public static final int MCCAOP_ADVERTISEMENT_OVERVIEW = 174;
        public static final int QUIET_PERIOD_REQUEST = 175;
        // 176: reserved
        public static final int QUIET_PERIOD_RESPONSE = 177;
        // 178-180: reserved
        public static final int QMF_POLICY = 181;
        public static final int ECAPC_POLICY = 182;
        public static final int CLUSTER_TIME_OFFSET = 183;
        public static final int INTRA_ACCESS_CATEGORY_PRIORITY = 184;
        public static final int SCS_DESCRIPTOR = 185;
        public static final int QLOAD_REPORT = 186;
        public static final int HCCA_TXOP_UPDATE_COUNT = 187;
        public static final int HIGHER_LAYER_STREAM_ID = 188;
        public static final int GCR_GROUP_ADDRESS = 189;
        public static final int ANTENNA_SECTOR_ID_PATTERN = 190;
        public static final int VHT_CAPABILITIES = 191;
        public static final int VHT_OPERATION = 192;
        public static final int EXTENDED_BSS_LOAD = 193;
        public static final int WIDE_BANDWIDTH_CHANNEL_SWITCH = 194;
        public static final int TRANSMIT_POWER_ENVELOPE = 195;
        public static final int CHANNEL_SWITCH_WRAPPER = 196;
        public static final int AID = 197;
        public static final int QUIET_CHANNEL = 198;
        public static final int OPERATING_MODE_NOTIFICATION = 199;
        public static final int UPSIM = 200;
        public static final int REDUCED_NEIGHBOR_REPORT = 201;
        public static final int TVHT_OPERATION = 202;
        // 203: reserved
        public static final int DEVICE_LOCATION = 204;
        public static final int WHITE_SPACE_MAP = 205;
        public static final int FINE_TIMING_MEASUREMENT_PARAMETERS = 206;
        public static final int S1G_OPEN_LOOP_LINK_MARGIN_INDEX = 207;
        public static final int RPS = 208;
        public static final int PAGE_SLICE = 209;
        public static final int AID_REQUEST = 210;
        public static final int AID_RESPONSE = 211;
        public static final int S1G_SECTOR_OPERATION = 212;
        public static final int S1G_BEACON_COMPATIBILITY = 213;
        public static final int SHORT_BEACON_INTERVAL = 214;
        public static final int CHANGE_SEQUENCE = 215;
        public static final int TWT = 216;
        public static final int S1G_CAPABILITIES = 217;
        // 218-219: reserved
        public static final int SST = 220;  // IEEE Stf 802.11ah
        public static final int VENDOR_SPECIFIC = 221;
        public static final int AUTHENTICATION_CONTROL = 222;
        public static final int TSF_TIMER_ACCURACY = 223;
        public static final int S1G_RELAY = 224;
        public static final int REACHABLE_ADDRESS = 225;
        public static final int S1G_RELAY_DISCOVERY = 226;
        public static final int AID_ANNOUNCEMENT = 228;
        public static final int PV1_PROBE_RESPONSE_OPTION = 229;
        public static final int EL_OPERATION = 230;
        public static final int SECTORIZED_GROUP_ID_LIST = 231;
        public static final int S1G_OPERATION = 232;
        public static final int HEADER_COMPRESSION = 233;
        public static final int SST_OPERATION = 234;
        public static final int MAD = 235;
        public static final int S1G_RELAY_ACTIVATION = 236;
        public static final int CAG_NUMBER = 237;
        // 238: reserved
        public static final int AP_CSN = 239;
        public static final int FILS_INDICATION = 240;
        public static final int DILS = 241;
        public static final int FRAGMENT = 242;
        // 243: reserved
        public static final int RSNXE = 244;
        // 245-254: reserved
        public static final int ELEMENT_ID_EXTENSION_PRESENT = 255;
    }

    // assigned Element ID Extension numbers
    public static class ExtID {
        public static final int ASSOCIATION_DELAY_INFO = 1;
        public static final int FILS_REQUEST_PARAMETERS = 2;
        public static final int FILS_KEY_CONFIRMATION = 3;
        public static final int FILS_SESSION = 4;
        public static final int FILS_HLP_CONTAINER = 5;
        public static final int FILS_IP_ADDRESS_ASSIGNMENT = 6;
        public static final int KEY_DELIVERY = 7;
        public static final int FILS_WRAPPED_DATA = 8;
        public static final int FTM_SYNCHRONIZATION_INFORMATION = 9;
        public static final int EXTENDED_REQUEST = 10;
        public static final int ESTIMATED_SERVICE_PARAMETERS_INBOUND = 11;
        public static final int FILS_PUBLIC_KEY = 12;
        public static final int FILS_NONCE = 13;
        public static final int FUTURE_CHANNEL_GUIDANCE = 14;
        public static final int SERVICE_HINT = 15;
        public static final int SERVICE_HASH = 16;
        public static final int CDMG_CAPABILITIES = 17;
        public static final int DYNAMIC_BANDWIDTH_CONTROL = 18;
        public static final int CDMG_EXTENDED_SCHEDULE = 19;
        public static final int SSW_REPORT = 20;
        public static final int CLUSTER_PROBE = 21;
        public static final int EXTENDED_CLUSTER_REPORT = 22;
        public static final int CLUSTER_SWITCH_ANNOUNCEMENT = 23;
        public static final int ENHANCED_BEAM_TRACKING = 24;
        public static final int SPSH_REPORT = 25;
        public static final int CLUSTERING_INTERFERENCE_ASSESSMENT = 26;
        // TODO 27-32
        public static final int DIFFIE_HELLMAN_PARAMETER = 32;
        public static final int PASSWORD_IDENTIFIER = 33;
        public static final int GLK_GCR_PARAMETER_SET = 34;
        public static final int HE_CAPABILITIES = 35;
        public static final int HE_OPERATION = 36;
        public static final int UORA_PARAMETER_SET = 37;
        public static final int MU_EDCA_PARAMETER_SET = 38;
        public static final int SPATIAL_REUSE_PARAMETER_SET = 39;
        public static final int GAS_EXTENSION = 40;
        public static final int NDP_FEEDBACK_REPORT_PARAMETER_SET = 41;
        public static final int BSS_COLOR_CHANGE_ANNOUNCEMENT = 42;
        public static final int QUIET_TIME_PERIOD_SETUP = 43;
        public static final int VENDOR_SPECIFIC_REQUEST = 44;
        public static final int ESS_REPORT = 45;
        public static final int OPS = 46;
        public static final int HE_BSS_LOAD = 47;
        // TODO 48-54
        public static final int MULTIPLE_BSSID_CONFIGURATION = 55;
        public static final int NON_INHERITANCE = 56;
        public static final int KNOWN_BSSID = 57;
        public static final int SHORT_SSID_LIST = 58;
        public static final int HE_6GHZ_BAND_CAPABILITIES = 59;
        public static final int UL_MU_POWER_CAPABILITIES = 60;
        // TODO 61-87
        public static final int MSCS_DESCRIPTOR = 88;
        public static final int TCLAS_MASK = 89;
        public static final int SUPPLEMENTAL_CLASS2_CAPABILITIES = 90;
        public static final int OCT_SOURCE = 91;
        public static final int REJECTED_GROUPS = 92;
        public static final int ANTI_CLOGGING_TOKEN_CONTAINER = 93;
        // 94-113: reserved
        public static final int AKM_SUITE_SELECTOR_ELEMENT = 114;
        // 115: reserved
        public static final int ORIGINATOR_PREFERRED_MCS = 116;
        // 117-255: reserved
    }

    // assigned vendor OUIs (selective, only 3-byte format)
    public static class Vendor {
        public static final int APPLE_AWDL = 0x0017F2;
        public static final int ARUBA = 0x000B86;
        public static final int Broadcom = 0x001018;
        public static final int CISCO = 0x00000C;
        public static final int CISCO2 = 0x000142;
        public static final int CISCO3 = 0x000143;
        public static final int CISCO_90 = 0x0000F8;
        public static final int CISCO_UBIQUITY = 0x001B67;
        public static final int CISCO_WIRELESS_AIRONET = 0x004096;
        public static final int EPIGRAM = 0x00904c;
        public static final int ESPRESSIF = 0x18fe34;
        public static final int GOOGLE = 0xf4f5e8;
        public static final int HP = 0x080009;
        public static final int HP_2 = 0x00805F;
        public static final int MEDIATEK = 0x000ce7;
        public static final int MICROSOFT = 0x0050f2;
        public static final int NETGEAR = 0x00146c;
        public static final int NXTV= 0x00037f;
        public static final int Qualcomm_1 = 0x00a0c6;
        public static final int Qualcomm_2 = 0x8cfdf0;
        public static final int Quantenna = 0x002686;
        public static final int RALINK = 0x000c43;
        public static final int RUCKUS = 0x001392;
        public static final int Ubiquiti_1= 0x00156d;
        public static final int Ubiquiti_2= 0x44d9e7;
        public static final int WFA =0x506F9A;
    }

    // element names
    private static final Map<Integer, String> ieNames = new Hashtable<>();
    static {
        // element names
        ieNames.put(ID.SSID, "SSID");
        ieNames.put(ID.SUPPORTED_RATES, "Supported Rates");
        // 2: reserved
        ieNames.put(ID.DSSS_PARAMETER_SET, "DSSS Parameter set");
        // 4: reserved
        ieNames.put(ID.TIM, "Traffic Indication Map (TIM)");
        ieNames.put(ID.IBSS_PARAMETER_SET, "IBSS Parameter Set");
        ieNames.put(ID.COUNTRY, "Country Information");
        // 8-9: reserved
        ieNames.put(ID.REQUEST, "Request");
        ieNames.put(ID.BSS_LOAD, "BSS Load");
        ieNames.put(ID.EDCA_PARAMETER_SET, "EDCA Parameter Set");
        ieNames.put(ID.TSPEC, "Traffic Specification (TSPEC)");
        ieNames.put(ID.TCLAS, "Traffic Classification (TCLAS)");
        ieNames.put(ID.SCHEDULE, "Schedule");
        ieNames.put(ID.CHALLENGE_TEXT, "Challenge Text");
        // 17-31: reserved
        ieNames.put(ID.POWER_CONSTRAINT, "Power Constraint");
        ieNames.put(ID.POWER_CAPABILITY, "Power Capability");
        ieNames.put(ID.TPC_REQUEST, "TPC Request");
        ieNames.put(ID.TPC_REPORT, "TPC Report");
        ieNames.put(ID.SUPPORTED_CHANNELS, "Supported Channels");
        ieNames.put(ID.CHANNEL_SWITCH_ANNOUNCEMENT, "Channel Switch Announcement");
        ieNames.put(ID.MEASUREMENT_REQUEST, "Measurement Request");
        ieNames.put(ID.MEASUREMENT_REPORT, "Measurement Report");
        ieNames.put(ID.QUIET, "Quiet");
        ieNames.put(ID.IBSS_DFS, "IBSS DFS");
        ieNames.put(ID.ERP, "ERP");
        ieNames.put(ID.TS_DELAY, "TS Delay");
        ieNames.put(ID.TCLAS_PROCESSING, "TCLAS Processing");
        ieNames.put(ID.HT_CAPABILITIES, "HT Capabilities");
        ieNames.put(ID.QOS_CAPABILITY, "QoS Capability");
        // 47: reserved, but keeping the obsolete assignment as it was observed in HP devices
        ieNames.put(ID.ERP_INFORMATION_OLD, "ERP Information (Obsolete)");
        ieNames.put(ID.RSN, "RSN");
        // 49: reserved
        ieNames.put(ID.EXTENDED_SUPPORT_RATES, "Extended Supported Rates");
        ieNames.put(ID.AP_CHANNEL_REPORT, "AP Channel Report");
        ieNames.put(ID.NEIGHBOR_REPORT, "Neighbor Report");
        ieNames.put(ID.RCPI, "RCPI");
        ieNames.put(ID.MDE, "Mobility Domain Element (MDE)");
        ieNames.put(ID.FTE, "Fast BSS Transition Element (FTE)");
        ieNames.put(ID.TIE, "Timeout Interval Element (TIE)");
        ieNames.put(ID.RDE, "RIC Data Element (RDE");
        ieNames.put(ID.DSE_REGISTERED_LOCATION, "DSE Registered Location");
        ieNames.put(ID.SUPPORTED_OPERATING_CLASSES, "Supported Operating Classes");
        ieNames.put(ID.EXTENDED_CHANNEL_SWITCH_ANNOUNCEMENT, "Extended Channel Switch Announcement");
        ieNames.put(ID.HT_OPERATION, "HT Operation");
        ieNames.put(ID.SECONDARY_CHANNEL_OFFSET, "Secondary Channel Offset");
        ieNames.put(ID.BSS_AVERAGE_ACCESS_DELAY, "BSS Average Access Delay");
        ieNames.put(ID.ANTENNA, "Antenna");
        ieNames.put(ID.RSNI, "RSNI");
        ieNames.put(ID.MEASUREMENT_PILOT_TRANSMISSION, "Measurement Pilot Transmission");
        ieNames.put(ID.BSS_AVAILABLE_ADMISSION_CAPACITY, "BSS Available Admission Capacity");
        ieNames.put(ID.BSS_AC_ACCESS_DELAY, "BSS AC Access Delay");
        ieNames.put(ID.TIME_ADVERTISEMENT, "Time Advertisement");
        ieNames.put(ID.RM_ENABLED_CAPABILITIES, "RM Enabled Capabilities");
        ieNames.put(ID.MULTIPLE_BSSID, "Multiple BSSID");
        ieNames.put(ID._20_40_BSS_COEXISTENCE, "20/40 BSS Coexistence");
        ieNames.put(ID._20_40_BSS_INTOLERANT_CHANNEL_REPORT, "20/40 BSS Intolerant Channel Report");
        ieNames.put(ID.OVERLAPPING_BSS_SCAN_PARAMETERS, "Overlapping BSS Scan Parameters");
        ieNames.put(ID.RIC_DESCRIPTOR, "RIC Descriptor");
        ieNames.put(ID.MME, "Management MIC Element (MME)");
        // 77: reserved
        ieNames.put(ID.EVENT_REQUEST, "Event Request");
        ieNames.put(ID.EVENT_REPORT, "Event Report");
        ieNames.put(ID.DIAGNOSTIC_REQUEST, "Diagnostic Request");
        ieNames.put(ID.DIAGNOSTIC_REPORT, "Diagnostic Report");
        ieNames.put(ID.LOCATION_PARAMETERS, "Location Parameters");
        ieNames.put(ID.NONTRANSMITTED_BSSID_CAPABILITY, "Nontransmitted BSSID Capability");
        ieNames.put(ID.SSID_LIST, "SSID List");
        ieNames.put(ID.MULTIPLE_BSSID_INDEX, "Multiple BSSID Index");
        ieNames.put(ID.FMS_DESCRIPTOR, "FMS Descriptor");
        ieNames.put(ID.FMS_REQUEST, "FMS Request");
        ieNames.put(ID.FMS_RESPONSE, "FMS Response");
        ieNames.put(ID.QOS_TRAFFIC_CAPABILITY, "QoS Traffic Capability");
        ieNames.put(ID.BSS_MAX_IDLE_PERIOD, "BSS Max Idle Period");
        ieNames.put(ID.TFS_REQUEST, "TFS Request");
        ieNames.put(ID.TFS_RESPONSE, "TFS Response");
        ieNames.put(ID.WNM_SLEEP_MODE, "WNM-Sleep Mode");
        ieNames.put(ID.TIM_BROADCAST_REQUEST, "TIM Broadcast Request");
        ieNames.put(ID.TIM_BROADCAST_RESPONSE, "TIM Broadcast Response");
        ieNames.put(ID.COLOCATED_INTERFERENCE_REPORT, "Colocated Interference Report");
        ieNames.put(ID.CHANNEL_USAGE, "Channel Usage");
        ieNames.put(ID.TIME_ZONE, "Time Zone");
        ieNames.put(ID.DMS_REQUEST, "DMS Request");
        ieNames.put(ID.DMS_RESPONSE, "DMS Response");
        ieNames.put(ID.LINK_IDENTIFIER, "Link Identifier");
        ieNames.put(ID.WAKEUP_SCHEDULE, "Wakeup Schedule");
        // 103: reserved
        ieNames.put(ID.CHANNEL_SWITCH_TIMING, "Channel Switch Timing");
        ieNames.put(ID.PTI_CONTROL, "PTI Control");
        ieNames.put(ID.TPU_BUFFER_STATUS, "TPU Buffer Status");
        ieNames.put(ID.INTERWORKING, "Interworking");
        ieNames.put(ID.ADVERTISEMENT_PROTOCOL, "Advertisement Protocol");
        ieNames.put(ID.EXPEDITED_BANDWIDTH_REQUEST, "Expedited Bandwidth Request");
        ieNames.put(ID.QOS_MAP, "QoS Map");
        ieNames.put(ID.ROAMING_CONSORTIUM, "Roaming Consortium");
        ieNames.put(ID.EMERGENCY_ALERT_IDENTIFIER, "Emergency Alert Identifier");
        ieNames.put(ID.MESH_CONFIGURATION, "Mesh Configuration");
        ieNames.put(ID.MESH_ID, "Mesh ID");
        ieNames.put(ID.MESH_LINK_METRIC_REPORT, "Mesh Link Metric Report");
        ieNames.put(ID.CONGESTION_NOTIFICATION, "Congestion Notification");
        ieNames.put(ID.MESH_PEERING_MANAGEMENT, "Mesh Peering Management");
        ieNames.put(ID.MESH_CHANNEL_SWITCH_PARAMETERS, "Mesh Channel Switch Parameters");
        ieNames.put(ID.MESH_AWAKE_WINDOW, "Mesh Awake Window");
        ieNames.put(ID.BEACON_TIMING, "Beacon Timing");
        ieNames.put(ID.MCCAOP_SETUP_REQUEST, "MCCAOP Setup Request");
        ieNames.put(ID.MCCAOP_SETUP_REPLY, "MCCAOP SETUP Reply");
        ieNames.put(ID.MCCAOP_ADVERTISEMENT, "MCCAOP Advertisement");
        ieNames.put(ID.MCCAOP_TEARDOWN, "MCCAOP Teardown");
        ieNames.put(ID.GANN, "Gate Announcement (GANN)");
        ieNames.put(ID.RANN, "Root Announcement (RANN)");
        ieNames.put(ID.EXTENDED_CAPABILITIES, "Extended Capabilities");
        // 128-129: reserved
        ieNames.put(ID.PREQ, "Mesh Path Request (PREQ)");
        ieNames.put(ID.PREP, "Mesh Path Reply (PREP)");
        ieNames.put(ID.PERR, "Mesh Path Error (PERR)");
        // 133-136: reserved
        ieNames.put(ID.PXU, "Proxy Update (PXU)");
        ieNames.put(ID.PXUC, "Proxy Update Confirmation (PXUC)");
        ieNames.put(ID.AUTHENTICATED_MESH_PEERING_EXCHANGE, "Authenticated Mesh Peering Exchange");
        ieNames.put(ID.MIC, "Message Integrity Code (MIC)");
        ieNames.put(ID.DESTINATION_URI, "Destination URI");
        ieNames.put(ID.U_APSD_COEXISTENCE, "U-APSD Coexistence");
        ieNames.put(ID.DMG_WAKEUP_SCHEDULE, "DMG Wakeup Schedule");
        ieNames.put(ID.EXTENDED_SCHEDULE, "Extended Schedule");
        ieNames.put(ID.STA_AVAILABILITY, "STA Availability");
        ieNames.put(ID.DMG_TSPEC, "DMG TSPEC");
        ieNames.put(ID.NEXT_DMG_ATI, "Next DMG ATI");
        ieNames.put(ID.DMG_CAPABILITIES, "DMG Capabilities");
        // 149-150: reserved
        ieNames.put(ID.DMG_OPERATION, "DMG Operating");
        ieNames.put(ID.DMG_BSS_PARAMETER_CHANGE, "DMG BSS Parameter Change");
        ieNames.put(ID.DMG_BEAM_REFINEMENT, "DMG Beam Refinement");
        ieNames.put(ID.CHANNEL_MEASUREMENT_FEEDBACK, "Channel Measurement Feedback");
        // 155-156: reserved
        ieNames.put(ID.AWAKE_WINDOW, "Awake Window");
        ieNames.put(ID.MULTI_BAND, "Multi Band");
        ieNames.put(ID.ADDBA_EXTENSION, "ADDBA Extension");
        ieNames.put(ID.NEXT_PCP_LIST, "NEXT PCP List");
        ieNames.put(ID.PCP_HANDOVER, "PCP Handover");
        ieNames.put(ID.DMG_LINK_MARGIN, "DMG Link Margin");
        ieNames.put(ID.SWITCHING_STREAM, "Switching Stream");
        ieNames.put(ID.SESSION_TRANSITION, "Session Transition");
        // 165: reserved
        ieNames.put(ID.CLUSTER_REPORT, "Cluster Report");
        ieNames.put(ID.RELAY_CAPABILITIES, "Relay Capabilities");
        ieNames.put(ID.RELAY_TRANSFER_PARAMETER_SET, "Relay Transfer Parameter Set");
        ieNames.put(ID.BEAMLINK_MAINTENANCE, "Beamlink Maintenance");
        ieNames.put(ID.MMS, "Multiple MAC Sublayers (MMS)");
        ieNames.put(ID.U_PID, "U-PID");
        ieNames.put(ID.DMG_LINK_ADAPTION_ACKNOWLEDGMENT, "DMG Link Adaption Acknowledgment");
        // 173: reserved
        ieNames.put(ID.MCCAOP_ADVERTISEMENT_OVERVIEW, "MCCAOP Advertisement Overview");
        ieNames.put(ID.QUIET_PERIOD_REQUEST, "Quiet Period Request");
        // 176: reserved
        ieNames.put(ID.QUIET_PERIOD_RESPONSE, "Quiet Period Response");
        // 178-180: reserved
        ieNames.put(ID.QMF_POLICY, "QMF Policy");
        ieNames.put(ID.ECAPC_POLICY, "ECAPC Policy");
        ieNames.put(ID.CLUSTER_TIME_OFFSET, "Cluster Time Offset");
        ieNames.put(ID.INTRA_ACCESS_CATEGORY_PRIORITY, "Intra-Access Category Priority");
        ieNames.put(ID.SCS_DESCRIPTOR, "SCS Descriptor");
        ieNames.put(ID.QLOAD_REPORT, "QLOAD Report");
        ieNames.put(ID.HCCA_TXOP_UPDATE_COUNT, "HCCA TXOP Update Count");
        ieNames.put(ID.HIGHER_LAYER_STREAM_ID, "Higher Layer Stream ID");
        ieNames.put(ID.GCR_GROUP_ADDRESS, "GCR Group Address");
        ieNames.put(ID.ANTENNA_SECTOR_ID_PATTERN, "Antenna Sector ID Pattern");
        ieNames.put(ID.VHT_CAPABILITIES, "VHT Capabilities");
        ieNames.put(ID.VHT_OPERATION, "VHT Operation");
        ieNames.put(ID.EXTENDED_BSS_LOAD, "Extended BSS Load");
        ieNames.put(ID.WIDE_BANDWIDTH_CHANNEL_SWITCH, "Wide Bandwidth Channel Switch");
        ieNames.put(ID.TRANSMIT_POWER_ENVELOPE, "Transmit Power Envelope");
        ieNames.put(ID.CHANNEL_SWITCH_WRAPPER, "Channel Switch Wrapper");
        ieNames.put(ID.AID, "AID");
        ieNames.put(ID.QUIET_CHANNEL, "Quiet Channel");
        ieNames.put(ID.OPERATING_MODE_NOTIFICATION, "Operating Mode Notification");
        ieNames.put(ID.UPSIM, "Unscheduled Power Save Indication Map (UPSIM)");
        ieNames.put(ID.REDUCED_NEIGHBOR_REPORT, "Reduced Neighbor Report");
        ieNames.put(ID.TVHT_OPERATION, "TVHT Operation");
        // 203: reserved
        ieNames.put(ID.DEVICE_LOCATION, "Device Location");
        ieNames.put(ID.WHITE_SPACE_MAP, "White Space Map");
        ieNames.put(ID.FINE_TIMING_MEASUREMENT_PARAMETERS, "Fine Timing Measurement Parameters");
        ieNames.put(ID.S1G_OPEN_LOOP_LINK_MARGIN_INDEX, "S1G Open-Loop Link Margin Index");
        ieNames.put(ID.RPS, "Raw Parameter Set (RPS)");
        ieNames.put(ID.PAGE_SLICE, "Page Slice");
        ieNames.put(ID.AID_REQUEST, "AID Request");
        ieNames.put(ID.AID_RESPONSE, "AID Response");
        ieNames.put(ID.S1G_SECTOR_OPERATION, "S1G Sector Operation");
        ieNames.put(ID.S1G_BEACON_COMPATIBILITY, "S1G Beacon Compatibility");
        ieNames.put(ID.SHORT_BEACON_INTERVAL, "Short Beacon Interval");
        ieNames.put(ID.CHANGE_SEQUENCE, "Change Sequence");
        ieNames.put(ID.TWT, "Target Wake Time (TWT)");
        ieNames.put(ID.S1G_CAPABILITIES, "S1G Capabilities");
        // 218-219: reserved
        ieNames.put(ID.SST, "Subchannel Selective Transmission (SST)");
        ieNames.put(ID.VENDOR_SPECIFIC, "Vendor Specific");
        ieNames.put(ID.AUTHENTICATION_CONTROL, "Authentication Control");
        ieNames.put(ID.TSF_TIMER_ACCURACY, "TSF Timer Accuracy");
        ieNames.put(ID.S1G_RELAY, "S1G Relay");
        ieNames.put(ID.REACHABLE_ADDRESS, "Reachable Address");
        ieNames.put(ID.S1G_RELAY_DISCOVERY, "S1G Relay Discovery");
        ieNames.put(ID.AID_ANNOUNCEMENT, "AID Announcement");
        ieNames.put(ID.PV1_PROBE_RESPONSE_OPTION, "PV1 Probe Response Option");
        ieNames.put(ID.EL_OPERATION, "EL Operation");
        ieNames.put(ID.SECTORIZED_GROUP_ID_LIST, "Sectorized Group ID List");
        ieNames.put(ID.S1G_OPERATION, "S1G Operation");
        ieNames.put(ID.HEADER_COMPRESSION, "Header Compression");
        ieNames.put(ID.SST_OPERATION, "SST Operation");
        ieNames.put(ID.MAD, "Maximum Away Duration (MAD)");
        ieNames.put(ID.S1G_RELAY_ACTIVATION, "S1G Relay Activation");
        ieNames.put(ID.CAG_NUMBER, "CAG Number");
        // 238: reserved
        ieNames.put(ID.AP_CSN, "AP Configuration Sequence Number (AP-CSN)");
        ieNames.put(ID.FILS_INDICATION, "FILS Indication");
        ieNames.put(ID.DILS, "Differential Initial Link Setup (DILS)");
        ieNames.put(ID.FRAGMENT, "Fragment");
        // 243: reserved
        ieNames.put(ID.RSNXE, "RSN Extension Element (RSNXE)");
        // 245-254: reserved
        ieNames.put(ID.ELEMENT_ID_EXTENSION_PRESENT, "Element ID Extension Present");
    }

    // extension element names
    private static final Map<Integer, String> extIeNames = new Hashtable<>();
    static {
        extIeNames.put(ExtID.ASSOCIATION_DELAY_INFO, "Association Delay Info");
        extIeNames.put(ExtID.FILS_REQUEST_PARAMETERS, "FILS Request Parameters");
        extIeNames.put(ExtID.FILS_KEY_CONFIRMATION, "FILS Key Confirmation");
        extIeNames.put(ExtID.FILS_SESSION, "FILS Session");
        extIeNames.put(ExtID.FILS_HLP_CONTAINER, "FILS HLP Container");
        extIeNames.put(ExtID.FILS_IP_ADDRESS_ASSIGNMENT, "FILS IP Address Assignment");
        extIeNames.put(ExtID.KEY_DELIVERY, "Key Delivery");
        extIeNames.put(ExtID.FILS_WRAPPED_DATA, "Wrapped Data");
        extIeNames.put(ExtID.FTM_SYNCHRONIZATION_INFORMATION, "FTM Synchronization Information");
        extIeNames.put(ExtID.EXTENDED_REQUEST, "Extended Request");
        extIeNames.put(ExtID.ESTIMATED_SERVICE_PARAMETERS_INBOUND, "Estimated Service Parameters Inbound");
        extIeNames.put(ExtID.FILS_PUBLIC_KEY, "FILS Public Key");
        extIeNames.put(ExtID.FILS_NONCE, "FILS Nonce");
        extIeNames.put(ExtID.FUTURE_CHANNEL_GUIDANCE, "Future Channel Guidance");
        extIeNames.put(ExtID.SERVICE_HINT, "Service Hint");
        extIeNames.put(ExtID.SERVICE_HASH, "Service Hash");
        extIeNames.put(ExtID.CDMG_CAPABILITIES, "CDMG Capabilities");
        extIeNames.put(ExtID.DYNAMIC_BANDWIDTH_CONTROL, "Dynamic Bandwidth Control");
        extIeNames.put(ExtID.CDMG_EXTENDED_SCHEDULE, "CDMG Extended Schedule");
        extIeNames.put(ExtID.SSW_REPORT, "SSW Report");
        extIeNames.put(ExtID.CLUSTER_PROBE, "Cluster Probe");
        extIeNames.put(ExtID.EXTENDED_CLUSTER_REPORT, "Extended Cluster Report");
        extIeNames.put(ExtID.CLUSTER_SWITCH_ANNOUNCEMENT, "Cluster Switch Announcement");
        extIeNames.put(ExtID.ENHANCED_BEAM_TRACKING, "Enhanced Beam Tracking");
        extIeNames.put(ExtID.SPSH_REPORT, "SPSH Report");
        extIeNames.put(ExtID.CLUSTERING_INTERFERENCE_ASSESSMENT, "Clustering Interference Assessment)");
        // TODO 27-32
        extIeNames.put(ExtID.DIFFIE_HELLMAN_PARAMETER, "Diffie-Hellman Parameter");
        extIeNames.put(ExtID.PASSWORD_IDENTIFIER, "Password Identifier");
        extIeNames.put(ExtID.GLK_GCR_PARAMETER_SET, "GLK_GCR_PARAMETER_SET");
        extIeNames.put(ExtID.HE_CAPABILITIES, "HE Capabilities");
        extIeNames.put(ExtID.HE_OPERATION, "HE Operation");
        extIeNames.put(ExtID.UORA_PARAMETER_SET, "UORA Parameter Set");
        extIeNames.put(ExtID.MU_EDCA_PARAMETER_SET, "MU EDCA Parameter Set");
        extIeNames.put(ExtID.SPATIAL_REUSE_PARAMETER_SET, "Spatial Reuse Parameter Set");
        extIeNames.put(ExtID.GAS_EXTENSION, "GAS Extension");
        extIeNames.put(ExtID.NDP_FEEDBACK_REPORT_PARAMETER_SET, "NDP Feedback Report Parameter Set");
        extIeNames.put(ExtID.BSS_COLOR_CHANGE_ANNOUNCEMENT, "BSS Color Change Announcement");
        extIeNames.put(ExtID.QUIET_TIME_PERIOD_SETUP, "Quiet Time Period Setup");
        extIeNames.put(ExtID.VENDOR_SPECIFIC_REQUEST, "Vendor Specific Request");
        extIeNames.put(ExtID.ESS_REPORT, "ESS Report");
        extIeNames.put(ExtID.OPS, "OPS");
        extIeNames.put(ExtID.HE_BSS_LOAD, "HE BSS Load");
        // TODO 48-54
        extIeNames.put(ExtID.MULTIPLE_BSSID_CONFIGURATION, "Multiple BSSID Configuration");
        extIeNames.put(ExtID.NON_INHERITANCE, "Non-Inheritance");
        extIeNames.put(ExtID.KNOWN_BSSID, "Known BSSID");
        extIeNames.put(ExtID.SHORT_SSID_LIST, "Short SSID List");
        extIeNames.put(ExtID.HE_6GHZ_BAND_CAPABILITIES, "HE 6 GHz Band Capabilities");
        extIeNames.put(ExtID.UL_MU_POWER_CAPABILITIES, "UL MU Power Capabilities");
        // TODO 61-87
        extIeNames.put(ExtID.MSCS_DESCRIPTOR, "MSCS Descriptor");
        extIeNames.put(ExtID.TCLAS_MASK, "TCLAS Mask");
        extIeNames.put(ExtID.SUPPLEMENTAL_CLASS2_CAPABILITIES, "Supplemental Class2 Capabilities");
        extIeNames.put(ExtID.OCT_SOURCE, "OCT Source");
        extIeNames.put(ExtID.REJECTED_GROUPS, "Rejected Groups");
        extIeNames.put(ExtID.ANTI_CLOGGING_TOKEN_CONTAINER, "Anti-Clogging Token Container");
        // 94-113: reserved
        extIeNames.put(ExtID.AKM_SUITE_SELECTOR_ELEMENT, "AKM Suite Selector Element");
        // 115: reserved
        extIeNames.put(ExtID.ORIGINATOR_PREFERRED_MCS, "ORIGINATOR_PREFERRED_MCS");
        // 117-255: reserved
    }

    // vendor names
    private static final Map<Integer, String> vendorNames = new Hashtable<>();
    static {
        vendorNames.put(Vendor.APPLE_AWDL, "Apple Wireless Direct Link (AWDL)");
        vendorNames.put(Vendor.Broadcom, "Broadcom");
        vendorNames.put(Vendor.ARUBA, "Aruba");
        vendorNames.put(Vendor.CISCO, "Cisco");
        vendorNames.put(Vendor.CISCO2, "Cisco");
        vendorNames.put(Vendor.CISCO3, "Cisco");
        vendorNames.put(Vendor.CISCO_90, "Cisco");
        vendorNames.put(Vendor.CISCO_UBIQUITY, "Cisco");
        vendorNames.put(Vendor.CISCO_WIRELESS_AIRONET, "Cisco Aironet");
        vendorNames.put(Vendor.EPIGRAM, "Epigram");
        vendorNames.put(Vendor.ESPRESSIF, "Espressif");
        vendorNames.put(Vendor.GOOGLE, "Google");
        vendorNames.put(Vendor.HP, "Hewlett-Packard");
        vendorNames.put(Vendor.HP_2, "Hewlett-Packard");
        vendorNames.put(Vendor.MEDIATEK, "MediaTek");
        vendorNames.put(Vendor.MICROSOFT, "Microsoft");
        vendorNames.put(Vendor.NETGEAR, "Netgear");
        vendorNames.put(Vendor.NXTV, "NXTV");
        vendorNames.put(Vendor.Qualcomm_1, "Qualcomm");
        vendorNames.put(Vendor.Qualcomm_2, "Qualcomm");
        vendorNames.put(Vendor.Quantenna, "Quantenna");
        vendorNames.put(Vendor.RALINK, "Ralink Technology");
        vendorNames.put(Vendor.RUCKUS, "Ruckus");
        vendorNames.put(Vendor.Ubiquiti_1, "Ubiquiti");
        vendorNames.put(Vendor.Ubiquiti_2, "Ubiquiti");
        vendorNames.put(Vendor.WFA, "Wi-Fi Alliance");
    }

    public String getName(int id, int extId, ByteBuffer bytes) {
        // vendor specific
        if (id == ID.VENDOR_SPECIFIC) {
            int oui = 0;
            for (int i = 0; i < 3; i++) { // 24-bit OUI only
                oui = (oui << 8) + ((int)(bytes.get()) & 0xff);
            }
            bytes.rewind(); // return to original state
            return ieNames.get(ID.VENDOR_SPECIFIC) + " (" +
                    vendorNames.getOrDefault(oui, "???") + ")";
        }
        // everything else
        else return (id == ID.ELEMENT_ID_EXTENSION_PRESENT) ?
                extIeNames.getOrDefault(extId, "??? (ID Extension = " + extId + ")") :
                ieNames.getOrDefault(id, "??? (ID = " + id + ")");
    }
}