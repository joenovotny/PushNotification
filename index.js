const express = require('express');
const apn = require('apn');
const bodyParser = require('body-parser');

const app = express();
app.use(bodyParser.json());

const options = {
  token: {
    key: process.env.APNS_KEY.replace(/\\n/g, '\n'),
    keyId: process.env.APNS_KEY_ID,
    teamId: process.env.APNS_TEAM_ID
  },
  production: true
};

const apnProvider = new apn.Provider(options);

app.post('/send', async (req, res) => {
  const { token, message } = req.body;

  const notification = new apn.Notification();
  notification.alert = message;
  notification.topic = process.env.APNS_BUNDLE_ID;

  try {
    const result = await apnProvider.send(notification, token);
    res.status(200).send(result);
  } catch (error) {
    res.status(500).send(error);
  }
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});