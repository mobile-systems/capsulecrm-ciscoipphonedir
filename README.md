capsulecrm-ciscoipphonedir [![Build Status](https://travis-ci.org/coenrecruitment/capsulecrm-ciscoipphonedir.png)](https://travis-ci.org/coenrecruitment/capsulecrm-ciscoipphonedir)
==========================

Capsule CRM integration with your Cisco IP phone.

### Configuration

In `conf/application.conf`, add your Capsule CRM URL and Capsule CRM API token.
Capsule CRM users can find their API token by visiting `My Preferences` via their username menu in the Capsule navigation bar.

```ruby
capsulecrm.url="https://<yourdomain>.capsulecrm.com"
capsulecrm.token="<your token here>"
```

### Running

```scala
sbt package
```

```
java -jar capsule-cisco.jar
```