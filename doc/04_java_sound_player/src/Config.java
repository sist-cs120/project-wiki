public class Config {

	/*
	 * Audio HW
	 */
	public final static int HW_BUFFER_SIZE = 512;
	
	
	/*
	 * PHY 
	 */
	public final static short MAX_SHORT = (short) 0x7FFF;
	public final static short MIN_SHORT = (short) 0x8000;
	public final static int PHY_HWLATENCY = 17;

	//frame buffer
	public final static int TNBF_MAX_BUFFER_LEN = 74; //74 bytes
	
	//frame buffer phy payload size
	public final static int TNBF_MAX_PHY_DATA_LEN = 72; //71 bytes
	
	//frame buffer mac payload size
	public final static int TNBF_MAX_MAC_DATA_LEN = 69; //71 bytes
			
	
	// Sampling Rate
	public final static int PHY_RX_SAMPLING_RATE = 48000;
	public final static int PHY_TX_SAMPLING_RATE = 48000;
	
	// Carrier Frequency
	public final static int PHY_CARRIER_FREQ = 10000;
	
	// Symbol duration
	public final static int PHY_SYMBOL_RATE = 12000;
	
	//Samples per Symbol
	public final static int PHY_RX_SAMPLES_PER_SYMBOL = PHY_RX_SAMPLING_RATE/PHY_SYMBOL_RATE;
	public final static int PHY_TX_SAMPLES_PER_SYMBOL = PHY_TX_SAMPLING_RATE/PHY_SYMBOL_RATE;
	
	//Samples per byte
	public final static int PHY_RX_SAMPLES_PER_BYTE = PHY_RX_SAMPLES_PER_SYMBOL*8;
	
	//Preamble Size (Unit: Symbols)
	public final static int PHY_PRE_SIZE = 16;
	public final static int PHY_PRE_FREQ_MIN = 2000;
	public final static int PHY_PRE_FREQ_MAX = 20000;
	
	//Rx dsp buffer size (unit: samples)
	public final static int PHY_RX_SYNC_BF_SIZE = PHY_RX_SAMPLES_PER_SYMBOL*PHY_PRE_SIZE;
	public final static int PHY_RX_DEC_BF_SIZE = PHY_RX_SAMPLES_PER_SYMBOL*PHY_PRE_SIZE;
	
	//Rx dsp state
	public final static int PHY_RX_DSP_STATE_SYNC = 0;
	public final static int PHY_RX_DSP_STATE_DECODE = 1;
	
	
	//read and write buffer size in sample
	public final static int PHY_RX_LINEBUFFER_SIZE = 8; //unit: sample
	public final static int PHY_TX_LINEBUFFER_SIZE = 8; //unit: sample
	
	
	/*
	 * 	  HW	   Dump.bin
	 *		/\	    /\
	 *		|_______|
	 *			|
	 *			|Sink
	 *		____|____
	 *		/\	    /\
	 *		|		|
	 *	TXDSP		Inject.bin
	 * 
	*/
	
	// Tx Sink output
	public final static int PHY_TX_SINK_OUTPUT_HW = 1<<0; // tx to hw sound card
	public final static int PHY_TX_SINK_OUTPUT_FILE = 1<<1; // to file for debug
	public final static String TX_SINK_DUMP_FILE = "PHY_Tx_Dump.bin";
	public final static int PHY_TX_SINK_OUTPUT_DEFAULT = PHY_TX_SINK_OUTPUT_HW | PHY_TX_SINK_OUTPUT_FILE;
//	public final static int PHY_TX_SINK_OUTPUT_DEFAULT = PHY_TX_SINK_OUTPUT_HW;
	
	// Tx Sink input
	public final static int PHY_TX_SINK_INPUT_DSP = 0; // from tx signal generator 
	public final static int PHY_TX_SINK_INPUT_FILE = 1; // from offline .bin for debug
	public final static String TX_SINK_INJECT_FILE = "PHY_Tx_Inject.bin";
	public final static int PHY_TX_SINK_INPUT_DEFAULT = PHY_TX_SINK_INPUT_DSP;
	
	/*
	 * 	  RXDSP		Dump.bin
	 *		/\	    /\
	 *		|_______|
	 *			|
	 *			|Source
	 *		____|____
	 *		/\	    /\
	 *		|		|
	 *	HW		Inject.bin
	 * 
	*/
	
	// Rx Source input
	public final static int PHY_RX_SRC_INPUT_HW = 0; // from sound card
	public final static int PHY_RX_SRC_INPUT_FILE = 1; // from offline file for debug
	public final static String PHY_RX_SRC_INJECT_FILE = "PHY_Rx_Inject.bin";
	public final static int PHY_RX_SRC_INPUT_DEFAULT = PHY_RX_SRC_INPUT_HW; 
	
	// Rx Source output
	public final static int PHY_RX_SRC_OUTPUT_DSP = 1<<0; // to rx DSP 
	public final static int PHY_RX_SRC_OUTPUT_FILE = 1<<1; // dump to offline file for debug
	public final static String PHY_RX_SRC_DUMP_FILE = "PHY_Rx_Dump.bin";	
	public final static int PHY_RX_SRC_OUTPUT_DEFAULT = PHY_RX_SRC_OUTPUT_DSP | PHY_RX_SRC_OUTPUT_FILE;
//	public final static int PHY_RX_SRC_OUTPUT_DEFAULT = PHY_RX_SRC_OUTPUT_DSP;
	
	
	/*
	 * MAC
	 */
	
	public final static byte MAC_ADDR = (byte) 0x1;
	public final static int MAC_ACK_BYTE = 7;
	public final static int MAC_ACK_DURATION = MAC_ACK_BYTE*8*PHY_RX_SAMPLES_PER_SYMBOL*1000/PHY_TX_SAMPLING_RATE;
	public final static int MAC_TIMEOUT = MAC_ACK_BYTE*8*PHY_RX_SAMPLES_PER_SYMBOL*1000/PHY_TX_SAMPLING_RATE+8+2*PHY_HWLATENCY;
	
	public final static int MAC_STATE_IDLE = 0;
	public final static int MAC_STATE_TX = 1;
	public final static int MAC_STATE_RX = 2;
	public final static int MAC_STATE_TX_WAIT = 3;
	public final static int MAC_RETRY_LIMIT = 10;
}
