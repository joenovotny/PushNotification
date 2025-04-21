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
  production: true, // Set to false for development, true for production
};

const apnProvider = new apn.Provider(options);

app.post("/send", async (req, res) => {
  const { token, message, topic } = req.body;

  const notification = new apn.Notification();

  // ✅ This is where the extension gets triggered
  notification.payload = {
    aps: {
      alert: message,
      sound: "default",
      "mutable-content": 1 // ← Trigger your Notification Service Extension
    }
    // 🔁 You can include custom keys here (like media-url)
  };

  // Optional fallback alert setup (legacy)
  notification.alert = message;
  notification.sound = "default";

  notification.pushType = "alert";
  notification.topic = topic || process.env.APNS_BUNDLE_ID;

  try {
    const result = await apnProvider.send(notification, token);
    console.log("📤 Push Result:", result);
    res.status(200).json({ success: true, result });
  } catch (err) {
    console.error("❌ Push Error:", err);
    res.status(500).json({ success: false, error: err.message });
  }
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`🚀 Push server listening on port ${PORT}`);
});