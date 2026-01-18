package org.shiv.shared

object Constants {
    const val WEB_CLIENT_ID = "922324872160-mg0dgu6olpmqu66lpio0j5es2t17mjqi.apps.googleusercontent.com"


    const val PAYPAL_CLIENT_ID = ""
    const val PAYPAL_SECRET_ID = ""

    const val PAYPAL_AUTH_KEY = "$PAYPAL_CLIENT_ID:$PAYPAL_SECRET_ID"

    const val PAYPAL_AUTH_ENDPOINT = "https://api-m.sandbox.paypal.com/v1/oauth2/token"
    const val PAYPAL_CHECKOUT_ENDPOINT = "https://api-m.sandbox.paypal.com/v2/checkout/orders"

    const val RETURN_URL = "com.shiv.nutrisport://paypalpay?success=true"
    const val CANCEL_URL = "com.shiv.nutrisport://paypalpay?cancel=true"

    const val MAX_QUANTITY = 10
    const val MIN_QUANTITY = 1
}