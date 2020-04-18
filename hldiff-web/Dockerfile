## Build stage ##
FROM node:13.13.0-alpine3.11 AS build
WORKDIR /usr/src/app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

## Run Stage ##
FROM nginx:1.17.1-alpine
COPY --from=build /usr/src/app/dist/hldiff-web /usr/share/nginx/html
RUN rm /etc/nginx/conf.d/default.conf
COPY ./nginx.conf /etc/nginx/conf.d/hldiff.conf