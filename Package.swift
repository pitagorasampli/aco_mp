// swift-tools-version:5.3
import PackageDescription
let package = Package(
   name: "ACO",
   platforms: [
     .iOS(.v14),
   ],
   products: [
      .library(name: "aco", targets: ["aco"])
   ],
   targets: [
      .binaryTarget(
         name: "aco",
         url: "https://github.com/pitagorasampli/aco_mp/releases/download/0.0.13/aco.xcframework.zip",
         checksum:"a4542e93a5d302792b6daf1dae6849d1981778600877123ed98702afc2562644")
   ]
)