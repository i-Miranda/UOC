##
## Auto Generated makefile by CodeLite IDE
## any manual changes will be erased      
##
## Debug
ProjectName            :=UOC20242
ConfigurationName      :=Debug
WorkspaceConfiguration :=Debug
WorkspacePath          :=/home/ivan/20242_PR3
ProjectPath            :=/home/ivan/20242_PR3
IntermediateDirectory  :=build-$(ConfigurationName)
OutDir                 :=$(IntermediateDirectory)
CurrentFileName        :=
CurrentFilePath        :=
CurrentFileFullPath    :=
User                   :=Ivan Miranda
Date                   :=05/30/2025
CodeLitePath           :=/home/ivan/.codelite
MakeDirCommand         :=mkdir -p
LinkerName             :=gcc
SharedObjectLinkerName :=gcc -shared -fPIC
ObjectSuffix           :=.o
DependSuffix           :=.o.d
PreprocessSuffix       :=.o.i
DebugSwitch            :=-g 
IncludeSwitch          :=-I
LibrarySwitch          :=-l
OutputSwitch           :=-o 
LibraryPathSwitch      :=-L
PreprocessorSwitch     :=-D
SourceSwitch           :=-c 
OutputDirectory        :=./bin
OutputFile             :=./bin/$(ProjectName)d
Preprocessors          :=
ObjectSwitch           :=-o 
ArchiveOutputSwitch    := 
PreprocessOnlySwitch   :=-E 
ObjectsFileList        :=$(IntermediateDirectory)/ObjectsList.txt
PCHCompileFlags        :=
LinkOptions            :=  
IncludePath            :=  $(IncludeSwitch). $(IncludeSwitch). $(IncludeSwitch)./test/include $(IncludeSwitch)./UOCPlay/include 
IncludePCH             := 
RcIncludePath          := 
Libs                   := $(LibrarySwitch)UOCPlay 
ArLibs                 :=  "libUOCPlay.a" 
LibPath                := $(LibraryPathSwitch). $(LibraryPathSwitch)./lib 

##
## Common variables
## AR, CXX, CC, AS, CXXFLAGS and CFLAGS can be overridden using an environment variable
##
AR       := ar rcus
CXX      := gcc
CC       := gcc
CXXFLAGS :=  -gdwarf-2 -O0 -Wall $(Preprocessors)
CFLAGS   :=  -gdwarf-2 -O0 -Wall $(Preprocessors)
ASFLAGS  := 
AS       := as


##
## User defined environment variables
##
CodeLiteDir:=/usr/share/codelite
Objects0=$(IntermediateDirectory)/test_src_test.c$(ObjectSuffix) $(IntermediateDirectory)/test_src_test_pr1.c$(ObjectSuffix) $(IntermediateDirectory)/test_src_test_suite.c$(ObjectSuffix) $(IntermediateDirectory)/test_src_test_pr2.c$(ObjectSuffix) $(IntermediateDirectory)/test_src_test_pr3.c$(ObjectSuffix) $(IntermediateDirectory)/src_main.c$(ObjectSuffix) 



Objects=$(Objects0) 

##
## Main Build Targets 
##
.PHONY: all clean PreBuild PrePreBuild PostBuild MakeIntermediateDirs
all: MakeIntermediateDirs $(OutputFile)

$(OutputFile): $(IntermediateDirectory)/.d $(Objects) 
	@$(MakeDirCommand) "$(IntermediateDirectory)"
	@echo "" > $(IntermediateDirectory)/.d
	@echo $(Objects0)  > $(ObjectsFileList)
	$(LinkerName) $(OutputSwitch)$(OutputFile) @$(ObjectsFileList) $(LibPath) $(Libs) $(LinkOptions)

MakeIntermediateDirs:
	@$(MakeDirCommand) "$(IntermediateDirectory)"
	@$(MakeDirCommand) "$(OutputDirectory)"

$(IntermediateDirectory)/.d:
	@$(MakeDirCommand) "$(IntermediateDirectory)"

PreBuild:


##
## Objects
##
$(IntermediateDirectory)/test_src_test.c$(ObjectSuffix): test/src/test.c 
	$(CC) $(SourceSwitch) "/home/ivan/20242_PR3/test/src/test.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/test_src_test.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/test_src_test.c$(PreprocessSuffix): test/src/test.c
	$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/test_src_test.c$(PreprocessSuffix) test/src/test.c

$(IntermediateDirectory)/test_src_test_pr1.c$(ObjectSuffix): test/src/test_pr1.c 
	$(CC) $(SourceSwitch) "/home/ivan/20242_PR3/test/src/test_pr1.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/test_src_test_pr1.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/test_src_test_pr1.c$(PreprocessSuffix): test/src/test_pr1.c
	$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/test_src_test_pr1.c$(PreprocessSuffix) test/src/test_pr1.c

$(IntermediateDirectory)/test_src_test_suite.c$(ObjectSuffix): test/src/test_suite.c 
	$(CC) $(SourceSwitch) "/home/ivan/20242_PR3/test/src/test_suite.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/test_src_test_suite.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/test_src_test_suite.c$(PreprocessSuffix): test/src/test_suite.c
	$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/test_src_test_suite.c$(PreprocessSuffix) test/src/test_suite.c

$(IntermediateDirectory)/test_src_test_pr2.c$(ObjectSuffix): test/src/test_pr2.c 
	$(CC) $(SourceSwitch) "/home/ivan/20242_PR3/test/src/test_pr2.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/test_src_test_pr2.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/test_src_test_pr2.c$(PreprocessSuffix): test/src/test_pr2.c
	$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/test_src_test_pr2.c$(PreprocessSuffix) test/src/test_pr2.c

$(IntermediateDirectory)/test_src_test_pr3.c$(ObjectSuffix): test/src/test_pr3.c 
	$(CC) $(SourceSwitch) "/home/ivan/20242_PR3/test/src/test_pr3.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/test_src_test_pr3.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/test_src_test_pr3.c$(PreprocessSuffix): test/src/test_pr3.c
	$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/test_src_test_pr3.c$(PreprocessSuffix) test/src/test_pr3.c

$(IntermediateDirectory)/src_main.c$(ObjectSuffix): src/main.c 
	$(CC) $(SourceSwitch) "/home/ivan/20242_PR3/src/main.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/src_main.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/src_main.c$(PreprocessSuffix): src/main.c
	$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/src_main.c$(PreprocessSuffix) src/main.c

##
## Clean
##
clean:
	$(RM) -r $(IntermediateDirectory)


