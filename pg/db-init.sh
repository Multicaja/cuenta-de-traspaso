#!/bin/bash

##########################################################
#
# Script que permite crear la base de datos para jenkins
# además crea los archivos jenkins.properties temporales
# para la ejecución en jenkins
#
##########################################################

id=$(date +%s | sha256sum | base64 | head -c 32 ; echo)
id="ci_${id,,}"

cat <<EOF >./db-id.txt
$id
EOF

#lee el archivo ci.properies como plantilla y para crear uno nuevo llamado jenkins.properties estableciendo en
#el el id de la base de datos nueva
IFS=
text=$(cat ./environments/ci.properties) #lee el archivo ci.properties
text=$(echo $text | sed -e "s/{BD_ID}/$id/") #reemplaza {BD_ID} por el contenido de la variable $id

#crea un nuevo archivo jenkins.properties a partir de $text
pg_jenkins_properties="./environments/jenkins.properties"
echo "Creando archivo: $pg_jenkins_properties"
cat <<EOF >$pg_jenkins_properties
$text
EOF

#lee el archivo ci.properies como plantilla y para crear uno nuevo llamado jenkins.properties estableciendo en
#el el id de la base de datos nueva
IFS=
text=$(cat ../app/src/main/resources/ci.properties) #lee el archivo ci.properties
text=$(echo $text | sed -e "s/{BD_ID}/$id/") #reemplaza {BD_ID} por el contenido de la variable $id

#crea un nuevo archivo jenkins.properties a partir de $text
app_jenkins_properties="../app/src/main/resources/jenkins.properties"
echo "Creando archivo: $app_jenkins_properties"
cat <<EOF >$app_jenkins_properties
$text
EOF
