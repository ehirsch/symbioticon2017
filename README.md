## smartsteuer symbioticon 2017 YOMO plugin app

This is our proof of concept app for, and entry to, the symbioticon 2017 Hackathon in Hamburg.

## Setup

You'll need to use some AHOI API credentials, please put them into res/values/api-config.xml like this:

<?xml version="1.0" encoding="utf-8"?>
<resources>
	    <string name="api_base_path">https://banking-sandbox.starfinanz.de/ahoi/api/v2</string>
        <string name="api_key">Bearer TOKEN</string>
        <integer name="accessId">ID</integer>
        <integer name="accountId">ACCOUNT_ID</integer>
</resources>

To determine valid account id for your account please use the AHOI frontend page, the link
is included in your credential mail.

## Content

The main view should show you a widget to start our onboarding process. This should be self
explanatory.

However, you might have to modify some transactions by using the AHOI frontend OR to customize
MockBackendProvider.java to recognize given ones. This way you can test the whole flow.
