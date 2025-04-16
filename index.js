// index.js test
const express = require("express");
const apn = require("@parse/node-apn");
const bodyParser = require("body-parser");
const app = express();

app.use(bodyParser.json());

const options = {
  token: {
    key: process.env.APNS_KEY.replace(/\\n/g, "\n"),
    keyId: process.env.APNS_KEY_ID,
    teamId: process.env.APNS_TEAM_ID,
  },
  production: false,
};

const apnProvider = new apn.Provider(options);

app.post("/send", async (req, res) => {
  const { token, message } = req.body;

  const notification = new apn.Notification();
  notification.alert = message;
  notification.sound = "default";
  notification.topic = process.env.APNS_BUNDLE_ID;

  try {
    const result = await apnProvider.send(notification, token);
    console.log("Push Result:", result);
    res.status(200).json({ success: true, result });
  } catch (err) {
    console.error("Push Error:", err);
    res.status(500).json({ success: false, error: err.message });
  }
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`🚀 Push server listening on port ${PORT}`);
});
