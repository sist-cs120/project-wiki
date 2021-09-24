/*
  ==============================================================================

    This file contains the basic startup code for a JUCE application.

  ==============================================================================
*/

#include <JuceHeader.h>

#define PI acos(-1)

using namespace juce;

class Tester : public AudioIODeviceCallback {

public:
    Tester() {}

    void audioDeviceAboutToStart(AudioIODevice* device) override {}

    void audioDeviceStopped() override {}

    void audioDeviceIOCallback(const float** inputChannelData, int numInputChannels,
        float** outputChannelData, int numOutputChannels, int numSamples) override {
        // Generate Sine Wave Data
        int freq = 9000; // Hz
        float amp = 0.7;
        int sampleRate = 48000;
        int channelNum = 1;
        float dPhasePerSample = 2 * PI * ((float)freq / (float)sampleRate);
        float initPhase = 0;
        float data;

        for (int i = 0; i < numSamples; i++) {
            data = amp * sin(dPhasePerSample * i + initPhase);
            // Write the sample into the output channel 
            outputChannelData[0][i] = data;
        }
    }


};

//==============================================================================
int main(int argc, char* argv[])
{
    /* Initialize Player */
    AudioDeviceManager dev_manager;
    dev_manager.initialiseWithDefaultDevices(1,1);
    AudioDeviceManager::AudioDeviceSetup dev_info;
    dev_info = dev_manager.getAudioDeviceSetup();
    dev_info.sampleRate = 48000; // Setup sample rate to 48000 Hz
    dev_manager.setAudioDeviceSetup(dev_info, false);

    /* Add callback to AudioDeviceManager */
    std::unique_ptr<Tester> tester;
    if (tester.get() == nullptr)
    {
        tester.reset(new Tester());
        dev_manager.addAudioCallback(tester.get());
    }

    /* Terminate the process */
    std::cout << "Press any ENTER to stop.\n";
    getchar();
    dev_manager.removeAudioCallback(tester.get());

    return 0;
}

