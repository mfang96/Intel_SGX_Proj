/**
*
*@author manna: DSP_LAB 2016_17
*
*/


class Java_Main
{
static
	{
		System.loadLibrary("isv_app");
	
	}

//Java-Bindings for the following Native functions used in Remote Attestation : manna DSP_LAB 2016_17
private native int func_init_enclave_ra(int ret,int enclave_lost_retry_time,int busy_retry_time);
private native int config_extended_ID(int ret);
private native int send_MSG0(int ret);
private native int send_MSG1(int ret);
private native void verifyMSG2();
private native int send_MSG3(int ret);
private native void verifyMSG3_and_PutINEnclave();

public static void main(String[] args){
		int ret=0;
		int enclave_lost_retry_time = 1;
   		int busy_retry_time = 4;
		Java_Main jmain = new Java_Main();
		 // Preparation for remote attestation by configuring extended epid group id.
    		//Calling configuring epid manna: 2017 March
	
		ret=jmain.config_extended_ID(ret);
		//Calling init_enclave_function manna: 2017 March
		System.out.println("Value of ret for debugging "+ret);
		ret=jmain.func_init_enclave_ra(ret,enclave_lost_retry_time,busy_retry_time);
		System.out.println("Value of ret for debugging "+ret);
		 // The ISV application sends msg0 to the SP.
   		 // The ISV decides whether to support this extended epid group id.
		ret=jmain.send_MSG0(ret);
		// Remote attestation will be initiated the ISV server challenges the ISV
    		// app or if the ISV app detects it doesn't have the credentials
    		// (shared secret) from a previous attestation required for secure
		// sending MSG1 and recieving MSG2: manna 2017
		jmain.send_MSG1(ret);
		/*
		 *verifying  MSG2: manna 2017
		 */
		jmain.verifyMSG2();
		/* 
		*Sending MSG3: manna 2017
		*/
		ret=jmain.send_MSG3(ret);
		/*
		* Verifying MSG3 and putting the recieved secret in the enclave: manna 2017
		*/
		jmain.verifyMSG3_and_PutINEnclave();

  

	}

}
