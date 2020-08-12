#! /bin/sh
yell() { echo "$0: $*" >&2; }
die() { yell "$*"; exit 111; }
try() { "$@" || die "cannot $*"; }

DEV=dev

if [ "$ENVIRONMENT" != "$DEV" ]; then

  eval $(try /bin/bash get-secrets.sh -r $REGION -n $LEGACY_SECRET -p LEGACY )

  export SPRING_DATASOURCE_USERNAME="$LEGACY_username"
  export SPRING_DATASOURCE_PASSWORD="$LEGACY_password"
  export SPRING_DATASOURCE_URL="$LEGACY_DATASOURCE_URL"
fi

java $JVM_OPTS -Djava.security.egd=file:/dev/./urandom  -jar *.jar 
