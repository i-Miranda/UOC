##
## Auto Generated makefile by CodeLite IDE
## any manual changes will be erased      
##
## Debug
ProjectName            :=UOCPlay
ConfigurationName      :=Debug
WorkspaceConfiguration :=Debug
WorkspacePath          :=/home/ivan/20242_PR2
ProjectPath            :=/home/ivan/20242_PR2/UOCPlay
IntermediateDirectory  :=../build-$(WorkspaceConfiguration)/UOCPlay
OutDir                 :=$(IntermediateDirectory)
CurrentFileName        :=
CurrentFilePath        :=
CurrentFileFullPath    :=
User                   :=Ivan Miranda
Date                   :=04/29/2025
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
OutputDirectory        :=../lib
OutputFile             :=../lib/lib$(ProjectName).a
Preprocessors          :=
ObjectSwitch           :=-o 
ArchiveOutputSwitch    := 
PreprocessOnlySwitch   :=-E 
ObjectsFileList        :=$(IntermediateDirectory)/ObjectsList.txt
PCHCompileFlags        :=
LinkOptions            :=  
IncludePath            :=  $(IncludeSwitch). $(IncludeSwitch). $(IncludeSwitch)./include 
IncludePCH             := 
RcIncludePath          := 
Libs                   := 
ArLibs                 :=  
LibPath                := $(LibraryPathSwitch). 

##
## Common variables
## AR, CXX, CC, AS, CXXFLAGS and CFLAGS can be overridden using an environment variable
##
AR       := ar rcus
CXX      := gcc
CC       := gcc
CXXFLAGS :=  -gdwarf-2 $(Preprocessors)
CFLAGS   :=  -gdwarf-2 $(Preprocessors)
ASFLAGS  := 
AS       := as


##
## User defined environment variables
##
CodeLiteDir:=/usr/share/codelite
Objects0=$(IntermediateDirectory)/src_watchstack.c$(ObjectSuffix) $(IntermediateDirectory)/src_api.c$(ObjectSuffix) $(IntermediateDirectory)/src_subscription.c$(ObjectSuffix) $(IntermediateDirectory)/src_person.c$(ObjectSuffix) $(IntermediateDirectory)/src_show.c$(ObjectSuffix) $(IntermediateDirectory)/src_date.c$(ObjectSuffix) $(IntermediateDirectory)/src_film.c$(ObjectSuffix) $(IntermediateDirectory)/src_csv.c$(ObjectSuffix) 



Objects=$(Objects0) 

##
## Main Build Targets 
##
.PHONY: all clean PreBuild PrePreBuild PostBuild MakeIntermediateDirs
all: MakeIntermediateDirs $(IntermediateDirectory)/$(OutputFile)

$(IntermediateDirectory)/$(OutputFile): $(Objects)
	@$(MakeDirCommand) "$(IntermediateDirectory)"
	@echo "" > $(IntermediateDirectory)/.d
	@echo $(Objects0)  > $(ObjectsFileList)
	$(AR) $(ArchiveOutputSwitch)$(OutputFile) @$(ObjectsFileList)
	@echo rebuilt > $(IntermediateDirectory)/UOCPlay.relink

MakeIntermediateDirs:
	@$(MakeDirCommand) "$(IntermediateDirectory)"
	@$(MakeDirCommand) "$(OutputDirectory)"

$(IntermediateDirectory):
	@$(MakeDirCommand) "$(IntermediateDirectory)"

PreBuild:


##
## Objects
##
$(IntermediateDirectory)/src_watchstack.c$(ObjectSuffix): src/watchstack.c 
	$(CC) $(SourceSwitch) "/home/ivan/20242_PR2/UOCPlay/src/watchstack.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/src_watchstack.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/src_watchstack.c$(PreprocessSuffix): src/watchstack.c
	$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/src_watchstack.c$(PreprocessSuffix) src/watchstack.c

$(IntermediateDirectory)/src_api.c$(ObjectSuffix): src/api.c 
	$(CC) $(SourceSwitch) "/home/ivan/20242_PR2/UOCPlay/src/api.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/src_api.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/src_api.c$(PreprocessSuffix): src/api.c
	$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/src_api.c$(PreprocessSuffix) src/api.c

$(IntermediateDirectory)/src_subscription.c$(ObjectSuffix): src/subscription.c 
	$(CC) $(SourceSwitch) "/home/ivan/20242_PR2/UOCPlay/src/subscription.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/src_subscription.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/src_subscription.c$(PreprocessSuffix): src/subscription.c
	$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/src_subscription.c$(PreprocessSuffix) src/subscription.c

$(IntermediateDirectory)/src_person.c$(ObjectSuffix): src/person.c 
	$(CC) $(SourceSwitch) "/home/ivan/20242_PR2/UOCPlay/src/person.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/src_person.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/src_person.c$(PreprocessSuffix): src/person.c
	$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/src_person.c$(PreprocessSuffix) src/person.c

$(IntermediateDirectory)/src_show.c$(ObjectSuffix): src/show.c 
	$(CC) $(SourceSwitch) "/home/ivan/20242_PR2/UOCPlay/src/show.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/src_show.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/src_show.c$(PreprocessSuffix): src/show.c
	$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/src_show.c$(PreprocessSuffix) src/show.c

$(IntermediateDirectory)/src_date.c$(ObjectSuffix): src/date.c 
	$(CC) $(SourceSwitch) "/home/ivan/20242_PR2/UOCPlay/src/date.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/src_date.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/src_date.c$(PreprocessSuffix): src/date.c
	$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/src_date.c$(PreprocessSuffix) src/date.c

$(IntermediateDirectory)/src_film.c$(ObjectSuffix): src/film.c 
	$(CC) $(SourceSwitch) "/home/ivan/20242_PR2/UOCPlay/src/film.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/src_film.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/src_film.c$(PreprocessSuffix): src/film.c
	$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/src_film.c$(PreprocessSuffix) src/film.c

$(IntermediateDirectory)/src_csv.c$(ObjectSuffix): src/csv.c 
	$(CC) $(SourceSwitch) "/home/ivan/20242_PR2/UOCPlay/src/csv.c" $(CFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/src_csv.c$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/src_csv.c$(PreprocessSuffix): src/csv.c
	$(CC) $(CFLAGS) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/src_csv.c$(PreprocessSuffix) src/csv.c

##
## Clean
##
clean:
	$(RM) -r $(IntermediateDirectory)


